package com.dicoding.lifeu.retrofit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Session(var api_key: String? = null) : Parcelable
