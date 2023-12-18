const env = require('dotenv');
env.config();
const express = require('express');
const app = express();

const dbCon = require('./config/mongodb');
dbCon();

const userRoutes = require('./routes/user');

const port = process.env.PORT || 5000;

app.use(express.json());
app.use('/api/user', userRoutes);

app.listen(port, () => {
    console.log(`Server is running on port: ${port}`);
});