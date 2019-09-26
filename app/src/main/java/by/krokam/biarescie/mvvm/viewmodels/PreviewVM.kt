package by.krokam.biarescie.mvvm.viewmodels

import android.os.Handler
import by.krokam.biarescie.navigation.Screens

class PreviewVM : BaseVM(){
    override fun onCreate() {
        Handler().postDelayed({
            router.newRootScreen(Screens.SECTION_LIST_SCREEN)
        }, 3000)
    }
}