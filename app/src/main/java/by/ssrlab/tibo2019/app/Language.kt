package by.krokam.biarescie.app

import java.util.*

enum class Language(val value: Int, val locale: Locale) {
    BEL(1, Locale("be")),
    RUS(3, Locale("ru")),
    ENG(2, Locale.ENGLISH),
    PL(5, Locale("pl"))
}