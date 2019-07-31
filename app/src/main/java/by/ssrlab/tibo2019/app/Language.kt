package by.krokam.biarescie.app

import java.util.*

enum class Language(val value: Int, val locale: Locale) {
    BEL(1, Locale("be")),
    RUS(2, Locale("ru")),
    ENG(3, Locale.ENGLISH)
}