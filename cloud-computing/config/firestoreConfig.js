const {Firestore, Timestamp} = require('@google-cloud/firestore');

const db = new Firestore({
    projectId: 'capstone-product-project',
    keyFilename: './config/key.json'
});

module.exports = {
  db,
  Timestamp
};