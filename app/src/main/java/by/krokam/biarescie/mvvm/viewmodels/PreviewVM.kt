package by.krokam.biarescie.mvvm.viewmodels

import android.os.Handler
import by.krokam.biarescie.mvvm.ui.SectionListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class PreviewVM : BaseVM(){
    override fun onCreate() {
        Handler().postDelayed({
            router.newRootScreen(FragmentScreen { SectionListFragment() })
        }, 3000)
    }
}