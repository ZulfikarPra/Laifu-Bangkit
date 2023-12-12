const { db, Timestamp } = require('../../config/firestoreConfig');
const singaporeDate = Timestamp.now();

const getUserByName = async (nameToSearch) => {
  try {
    // Melakukan kueri ke Firestore untuk mencari pengguna dengan nama tertentu
    const querySnapshot = await db
      .collection('users')
      .where('name', '==', nameToSearch)
      .get();

    // Mengubah hasil kueri ke dalam bentuk array
    const users = querySnapshot.docs.map((doc) => {
      const data = doc.data();
      // Konversi Timestamp menjadi string yang dapat dibaca
      const formattedDate = new Date(
        data.date._seconds * 1000
      ).toLocaleString();
      // Tambahkan field date yang telah diubah ke objek data
      return { ...data, date: formattedDate };
    });

    if (users.length === 0) {
      // Data tidak ditemukan
      return { success: false, name: nameToSearch, message: 'User not found' };
    } else {
      // Data ditemukan
      return { success: true, data: users };
    }
  } catch (error) {
    console.log('Error:', error);
    return { success: false, message: 'Internal server error' };
  }
};

const addUser = async (user) => {
  const {
    name = '',
    age = 0,
    city = '',
    country = '',
    gender = '',
    occupation = '',
    email = '',
    userId = '',
  } = user;

  if (!name) {
    throw new Error('Name is required.');
  }

  try {
    const dataUser = await db.collection('users').add({
      name,
      age,
      city,
      country,
      gender,
      occupation,
      email,
      userId,
      date: singaporeDate,
    });

    const addedUser = await dataUser.get();
    const userData = addedUser.data();

    // Menghilangkan nanosekond dari properti date
    if (userData.date && userData.date.seconds) {
      userData.date = new Date(userData.date.seconds * 1000).toLocaleString();
    }

    return {
      success: true,
      message: 'User added successfully',
      data: userData,
    };
  } catch (error) {
    console.log('Error:', error);
    return { success: false, message: 'Internal server error' };
  }
};

const updateUser = async (userName, updatedData) => {
  try {
    // Mendapatkan referensi dokumen berdasarkan nama
    const querySnapshot = await db
      .collection('users')
      .where('name', '==', userName)
      .get();

    if (querySnapshot.empty) {
      return { success: false, user: userName, message: 'User not found' };
    }

    // Perbarui data pengguna
    const dataUser = querySnapshot.docs[0].ref;
    await dataUser.update(updatedData);

    return { success: true, message: 'User updated successfully' };
  } catch (error) {
    console.log('Error:', error);
    return { success: false, message: 'Internal server error' };
  }
};

module.exports = {
  getUserByName,
  addUser,
  updateUser,
};
