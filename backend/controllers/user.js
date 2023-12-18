const asyncHandler = require('express-async-handler');
const User = require('../models/user');
const generateToken = require('../utils/generateToken');

const register = asyncHandler(async (req, res) => {
  const {email, password} = req.body;

  const exist = await User.findOne({email});

  if (exist) {
    res.status(400);
    throw new Error('User already exists');
  }

  const user = await User.create({
    email,
    password,
  });
  
  if (user) {
    res.status(201).json({
      _id: user._id,
      name: user.name,
      email: user.email,
      token: generateToken(user._id),
    });
  } else {
    res.status(400).json({
      message: 'Invalid user data',
    })
    throw new Error('Invalid user data');
  }
})

const auth = asyncHandler(async (req, res) => {
  const {email, password} = req.body;

  const user = await User.findOne({email});

  if (user && (await user.matchPassword(password))) {
    res.status(200).json({
      _id: user._id,
      name: user.name,
      email: user.email,
      name: user.name,
      age: user.age,
      gender: user.gender,
      occupation: user.occupation,
      country: user.country,
      city: user.city,
      qualityofSleep: user.qualityofSleep,
      foodRecommendation: user.foodRecommendation,
      token: generateToken(user._id)
    });
  } else {
    res.status(401).json({
      message: 'Invalid email or password',
    });
    throw new Error('Invalid email or password');
  }
});

const update = asyncHandler(async (req, res) => {
  const id = req.params.id;
  //const {name, age, gender, occupation, country, city, qualityofSleep, foodRecommendation} = req.body;
  let result = ""
  for(var key in req.body){
    if(req.body[key] != null){
      try{
        await User.updateOne({_id: id}, {$set: {[key]: req.body[key]}});
      } catch(err){
        res.status(400).json({
          message: err.message,
        })
      }
      result = result + key + " has been updated to " + req.body[key] + "\n";
    } 
  }
  res.status(200).json({
    message: result,
  })
})

const getAll = asyncHandler(async (req, res) => {
  try{
    const users = await User.find({}).select('-password');
    res.status(200).json({
      users: users,
    })
  } catch(err){
    res.status(400).json({
      message: err.message,
    })
  }
});

module.exports = {
  register,
  auth,
  update,
  getAll
};