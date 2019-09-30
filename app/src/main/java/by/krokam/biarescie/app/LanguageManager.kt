package by.krokam.biarescie.app

import android.app.Application
import android.content.Context

class LanguageManager(private val app: Application) {

    private val PREFS_NAME = "HOLY_PREFS_2"
    private val LANGUAGE = "LANGUAGE"

    fun readLanguage(): Language {
        return when (app.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getInt(LANGUAGE, 1)) {
            3 -> Language.RUS
            2 -> Language.ENG
            5 -> Language.PL
            4-> Language.ZH
            else -> Language.BEL
        }
    }

    fun writeLocale(lang: Language) {
        app.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().putInt(LANGUAGE, lang.value).apply()
    }
}