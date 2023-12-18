package com.dicoding.lifeu.view.login

import android.content.ContentValues
import android.telecom.Call
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    private val _userDetail = MutableLiveData<LoginResponse>()


    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getUser(token: String, email: String, password: String): LiveData<LoginResponse> {
        val client = ApiConfig.getApiService(token).login(email, password)
        client.enqueue(object : DiffUtil.Callback<LoginResponse> {
            override fun onResponse(
                call : Call<LoginResponse>,
                response: Response<LoginResponse>
            ){
                if (response.isSuccessful){
                    _userDetail.value = response.body()
                } else {
                    Log.e(ContentValues.TAG, "OnFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "OnFailure: ${t.message}")
            }
        })
        return _userDetail
    }
}