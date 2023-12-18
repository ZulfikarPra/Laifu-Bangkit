const express = require('express');
const {
  register,
  auth,
  update,
  getAll
} = require('../controllers/user');

// eslint-disable-next-line new-cap
const router = express.Router();

router.route('/register').post(register);
router.route('/auth').post(auth);
router.route('/update/:id').put(update);
router.route('/getAll').get(getAll);

module.exports = router;