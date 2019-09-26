package com.tt.handsomeman.util

import android.content.SharedPreferences

import com.tt.handsomeman.HandymanApp

import javax.inject.Inject

import dagger.Module

@Module
class SharedPreferencesUtils @Inject
constructor(private val sharedPreferences: SharedPreferences) {

    fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit()
        if (data is String) {
            editor.putString(key, data as String)
        } else if (data is Boolean) {
            editor.putBoolean(key, data as Boolean)
        } else if (data is Float) {
            editor.putFloat(key, data as Float)
        } else if (data is Int) {
            editor.putInt(key, data as Int)
        } else if (data is Long) {
            editor.putLong(key, data as Long)
        } else {
            editor.putString(key, HandymanApp.instance!!.gSon!!.toJson(data))
        }
        editor.apply()
    }

    operator fun <T> get(key: String, anonymousClass: Class<T>): T {
        return if (anonymousClass == String::class.java) {
            sharedPreferences.getString(key, "") as T?
        } else if (anonymousClass == Boolean::class.java) {
            java.lang.Boolean.valueOf(sharedPreferences.getBoolean(key, false)) as T
        } else if (anonymousClass == Float::class.java) {
            java.lang.Float.valueOf(sharedPreferences.getFloat(key, 0f)) as T
        } else if (anonymousClass == Int::class.java) {
            Integer.valueOf(sharedPreferences.getInt(key, 0)) as T
        } else if (anonymousClass == Long::class.java) {
            java.lang.Long.valueOf(sharedPreferences.getLong(key, 0)) as T
        } else {
            HandymanApp.instance!!.gSon!!.fromJson(sharedPreferences.getString(key, ""), anonymousClass)
        }
    }
}
