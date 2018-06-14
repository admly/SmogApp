package com.millerindustries.smog.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import com.millerindustries.smog.const.NO_DATA


/**
 * Created by adam on 10.04.18.
 * Static functions for saving and loading user data
 */


private const val TAG = "SharedPrefsManager"

/**
 * Fun receives shared preferences name, app context and map with values to insert into shared prefs
 */
fun saveDataInSharedPrefs(shared_preferences_name: String, context: Context, userDataMap: HashMap<String, String>){
    val preferencesEditor = context.getSharedPreferences(shared_preferences_name, Activity.MODE_PRIVATE).edit()
    for((key, value) in userDataMap){
        preferencesEditor.putString(key, value)
        Log.d(TAG, "key: $key value: $value")
    }
    preferencesEditor.apply()
}

/**
 * Fun recieves shared preferences name, app context and key list to construct a map from it
 */
fun restoreDataMapFromSharedPrefs(shared_preferences_name: String, context: Context, keyList: List<String>): Map<String,String>{
    val preferences = context.getSharedPreferences(shared_preferences_name, Activity.MODE_PRIVATE)
    val userDataMap = mutableMapOf<String, String>()
    for(key in keyList){
            userDataMap[key] = preferences.getString(key, null)
    }
    return userDataMap
}

/**
 * Fun for getting a single value for key
 */
fun restoreDataStringFromSharedPrefs(shared_preferences_name: String, context: Context, key: String): String?
        = context.
        getSharedPreferences(shared_preferences_name, Activity.MODE_PRIVATE).getString(key, NO_DATA)

