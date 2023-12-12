const express = require('express');
const userRoutes = require('./routes/userRoutes');
const conditionUserRoutes = require('./routes/conditionUserRoutes');

const app = express();
const port = process.env.PORT || 3000;



app.use(express.json());
// api/user/fungsi/parameter(jika ada)
app.use('/api/user', userRoutes);

// api/conditionUser/fungsi/parameter(jika ada)
app.use('/api/conditionUser', conditionUserRoutes);

app.get('/', (req, res) => {
    res.send("Hello World!");
});

app.delete('/user', (req, res) => {
    res.send("Got a DELETE request at /user");
});


app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});
