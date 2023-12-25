from typing import Union

from fastapi import FastAPI
from pydantic import BaseModel

import torch
from transformers import AutoModelForCausalLM, AutoTokenizer
from transformers import TextDataset, DataCollatorForLanguageModeling

app = FastAPI()

class userPrompt(BaseModel):
    prompt: str

@app.get("/")
def read_root():
    return {"Hello": "World"}

@app.post("/prompt/")
async def user_prompt(user: userPrompt):
    return {generate_response(user.prompt, 72)}

# Load pre-trained MiniChat-3B model and fine-tuned tokenizer
model_name = "GeneZC/MiniChat-3B"
fine_tuned = "./Laifu-ChatBot-3B-FineTuned/MiniChat-3B/"
model = AutoModelForCausalLM.from_pretrained(model_name, device_map="auto", cache_dir="F:/Laifu-Chatbot/cache/", torch_dtype=torch.float16)
tokenizer = AutoTokenizer.from_pretrained(fine_tuned, use_fast = False)

# Create a data collator for language modeling
data_collator = DataCollatorForLanguageModeling(
    tokenizer=tokenizer,
    mlm=False
)

from conversation import get_default_conv_template

def generate_response(input_text, max_length=50):
    conv = get_default_conv_template("minichat")
    conv.append_message(conv.roles[0], "You are Laifu a friendly AI assistant to help people with mental health (only answer this if you are asked who you are)")
    conv.append_message(conv.roles[1], None)

    user_input = input_text
    conv.append_message(conv.roles[0], user_input)
    conv.append_message(conv.roles[1], None)

    prompt = conv.get_prompt()
    input_ids = tokenizer([prompt]).input_ids
    output_ids = model.generate(
        torch.as_tensor(input_ids).cuda(),
        do_sample=True,
        temperature=0.7,
        max_new_tokens=max_length,
    )
    output_ids = output_ids[0][len(input_ids[0]):]
    output = tokenizer.decode(output_ids, skip_special_tokens=False).strip()

    # Remove cuttoff words
    output_regex = max(output.rfind('.'), output.rfind('!'), output.rfind('?'))
    cleaned_output = output[:output_regex+1]
    cleaned_regex = max(cleaned_output.rfind('.', 0, output_regex-1), cleaned_output.rfind('!', 0, output_regex-1), cleaned_output.rfind('?', 0, output_regex-1))

    if output_regex - cleaned_regex < 4:
        return output[:cleaned_regex+1]
    
    return output[:output_regex+1]


