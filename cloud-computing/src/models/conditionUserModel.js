const { db, Timestamp } = require('../../config/firestoreConfig');
const singaporeDate = Timestamp.now();

const getConditionUserByUserId = async (userId) => {
  const conditionUserSnapshot = await db
    .collection('condition_users')
    .where('userId', '==', userId)
    .get();

  try {
    if (conditionUserSnapshot.empty) {
      return {
        success: false,
        user: userId,
        message: 'User condition not found',
      };
    } else {
      const conditionUserData = conditionUserSnapshot.docs.map((doc) => {
        const data = doc.data();
        // Konversi Timestamp menjadi string yang dapat dibaca
        const formattedDate = data.date?.seconds
          ? new Date(data.date.seconds * 1000).toLocaleString()
          : null;
        // Tambahkan field date yang telah diubah ke objek data
        return { ...data, date: formattedDate };
      });
      return { success: true, data: conditionUserData };
    }
  } catch (error) {
    console.error('Error getting user condition', error);
    return { success: false, error: 'Internal Server Error' };
  }
};

const addCondition = async (conditionUser) => {
  const {
    blood_pressure,
    bmi_category,
    daily_step,
    heart_rate,
    physical_activity_level,
    stress_level,
    userId,
  } = conditionUser;

  try {
    // Dapatkan informasi pengguna berdasarkan email
    const userQuery = await db
      .collection('users')
      .where('userId', '==', userId)
      .get();

    // Cek apakah pengguna ditemukan
    if (userQuery.empty) {
      return { success: false, message: 'User not found' };
    }

    // Ambil data pengguna pertama dari hasil query
    const userData = userQuery.docs[0].data();

    // Tambahkan data ke koleksi condition_user
    await db.collection('condition_users').add({
      blood_pressure,
      bmi_category,
      daily_step,
      date: singaporeDate,
      heart_rate,
      physical_activity_level,
      stress_level,
      userId: userData.userId, // Tambahkan field userId dari data pengguna
    });

    return { success: true, message: 'Condition added successfully' };
  } catch (error) {
    console.log('Error:', error);
    return { success: false, message: 'Internal server error' };
  }
};

module.exports = {
  getConditionUserByUserId,
  addCondition,
};
