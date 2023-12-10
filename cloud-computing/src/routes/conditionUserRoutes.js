const express = require('express');
const conditionUserController = require('../controllers/conditionUserController');

const router = express.Router();

router.get(
  '/getConditionUserByUserId/:userId',
  conditionUserController.getConditionUserByUserId
);
router.post('/addCondition', conditionUserController.addCondition);

module.exports = router;
