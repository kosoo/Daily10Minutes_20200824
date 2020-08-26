package kr.co.tjoeun.daily10minutes_20200824.utils

import android.content.Context

class ContextUtil {

    companion object {

        private val prefName = "Daily10MinutesPref"

        private val AUTO_LOGIN_CHECK = "AUTO_LOGIN_CHECK"

        fun setAutoLoginCheck(context: Context, isAuto : Boolean){

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN_CHECK, isAuto).apply() //수정모드

        }

        fun getAutoLoginCheck(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN_CHECK, false)
        }
    }

}