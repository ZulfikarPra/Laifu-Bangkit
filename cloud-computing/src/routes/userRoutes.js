const express = require('express');
const userController = require('../controllers/userController');

const router = express.Router();

router.get('/getUserByName/:name', userController.getUserByName);
router.post('/addUser', userController.addUser);
router.put('/updateUser/:name', userController.updateUser);

module.exports = router;
