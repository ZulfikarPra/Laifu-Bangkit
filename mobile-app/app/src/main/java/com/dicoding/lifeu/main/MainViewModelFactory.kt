package com.dicoding.lifeu.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.lifeu.di.Injection
import com.dicoding.lifeu.pref.UserRepository
import com.dicoding.lifeu.view.signup.SignupActivity

class MainViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->{
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignupActivity::class.java)->{
                SignupActivity(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : MainViewModelFactory? = null

        fun getInstance (context: Context):MainViewModelFactory{
            return  INSTANCE
                ?: synchronized(this){
                    MainViewModelFactory(
                        Injection.provideRepository(context)
                    )
                }
        }
    }
}