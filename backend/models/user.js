const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

const userSchema = new mongoose.Schema({
  email: {
    type: String,
    required: [true, 'Please enter an email'],
    unique: true,
    trim: true,
  },
  password: {
    type: String,
    required: [true, 'Please enter a password'],
    minlength: [6, 'Password should be at least 6 characters'],
    trim: true,
  },
  name: {
    type: String,
    required: false,
  },
  age: {
    type: Number,
    required: false,
  },
  gender: {
    type: String,
    required: false,
  },
  occupation: {
    type: String,
    required: false,
  },
  country: {
    type: String,
    required: false,
  },
  city: {
    type: String,
    required: false,
  },
  qualityofSleep: {
    type: Number,
    required: false,
  },
  foodRecommendation: {
    type: String,
    required: false,
  },
});

userSchema.pre('save', async function (next) {
  if (!this.isModified('password')) {
    next();
  }

  const salt = await bcrypt.genSalt();

  this.password = await bcrypt.hash(this.password, salt);
  next();
});

userSchema.methods.matchPassword = async function (password) {
  return await bcrypt.compare(password, this.password);
}

const User = mongoose.model('User', userSchema);

module.exports = User;