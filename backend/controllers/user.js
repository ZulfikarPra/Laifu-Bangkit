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

module.exports = {
  register,
  auth,
};