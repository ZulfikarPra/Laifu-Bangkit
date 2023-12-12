const conditionUserModel = require('../models/conditionUserModel');

const getConditionUserByUserId = async (req, res) => {
  const userId = req.params.userId;

  try {
    const conditionUser = await conditionUserModel.getConditionUserByUserId(
      userId
    );
    res.json(conditionUser);
  } catch (error) {
    console.error('Error getting user condition', error);
    res.status(500).json({ success: false, error: 'Internal Server Error' });
  }
};

const addCondition = async (req, res) => {
  const conditionData = req.body;

  try {
    const newCondition = await conditionUserModel.addCondition(conditionData);
    res.json(newCondition);
  } catch (error) {
    console.log('Error:', error);
    res.status(500).json({ success: false, message: 'Internal server error' });
  }
};

module.exports = {
  getConditionUserByUserId,
  addCondition,
};
