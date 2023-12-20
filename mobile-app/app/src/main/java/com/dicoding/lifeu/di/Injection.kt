package com.dicoding.lifeu.di

import android.content.Context
import com.dicoding.lifeu.pref.UserPreference
import com.dicoding.lifeu.pref.UserRepository
import com.dicoding.lifeu.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}