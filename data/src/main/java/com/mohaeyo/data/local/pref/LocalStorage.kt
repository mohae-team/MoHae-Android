package com.mohaeyo.data.local.pref

interface LocalStorage {
    fun saveToken(token: String, access: Boolean)
    fun getToken(isAccess: Boolean): String
    fun removeToken()
}