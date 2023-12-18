const express = require('express');
const {
  register,
  auth,
} = require('../controllers/user');

// eslint-disable-next-line new-cap
const router = express.Router();

router.route('/register').post(register);
router.route('/auth').post(auth);

module.exports = router;