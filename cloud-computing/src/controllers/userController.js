const userModel = require('../models/userModel');

const getUserByName = async (req, res) => {
  const nameToSearch = req.params.name;

  try {
    const users = await userModel.getUserByName(nameToSearch);
    res.json(users);
  } catch (error) {
    console.log('Error:', error);
    res.status(500).json({ success: false, message: 'Internal server error' });
  }
};

const addUser = async (req, res) => {
  const user = req.body;

  try {
    const newUser = await userModel.addUser(user);
    res.json(newUser);
  } catch (error) {
    console.log('Error:', error);
    res.status(500).json({ success: false, message: 'Internal server error' });
  }
};

const updateUser = async (req, res) => {
  const userName = req.params.name;
  const updatedData = req.body; // Data yang ingin diperbarui

  try {
    const updatedUser = await userModel.updateUser(userName, updatedData);
    res.json(updatedUser);
  } catch (error) {
    console.error('Error updating user by name', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

module.exports = {
  getUserByName,
  addUser,
  updateUser,
};
