package com.stockbit.stockbit.core.data.source.shared

import android.content.Context
import android.content.SharedPreferences

class SessionManager(private val context: Context) {

    companion object {
        const val ACCOUNT_HAS_LOGIN = "ACCOUNT_HAS_LOGIN"
    }

    private var pref: SharedPreferences = context.applicationContext.getSharedPreferences("LalloSession", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = pref.edit()

    //SETTER
    fun setToken(token: String) {
        editor.putString(ACCOUNT_HAS_LOGIN, token)
        editor.apply()
    }


    //GETTER
    fun hasLogin() = pref.getBoolean(ACCOUNT_HAS_LOGIN, false)


    //CLEAR
    fun clearAccount(){
        editor.remove(ACCOUNT_HAS_LOGIN)
        editor.apply()
    }

}