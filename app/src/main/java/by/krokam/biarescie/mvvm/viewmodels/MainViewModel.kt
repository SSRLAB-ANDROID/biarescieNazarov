package by.krokam.biarescie.mvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import by.krokam.biarescie.app.LanguageManager
import by.krokam.biarescie.data.Repository2
import by.krokam.biarescie.mvvm.ui.PreviewFragment
import by.krokam.biarescie.navigation.Screens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val languageManager = LanguageManager(app)
    var language = languageManager.readLanguage()
        set(value) {
            field = value
            languageManager.writeLocale(value)
            repo.setLanguage(value)
        }

    val repo = Repository2(app, language)
    private val cicerone = Cicerone.create()
    val router = cicerone.router
    val navigatorHolder = cicerone.getNavigatorHolder()

    var isInited = false

    init {
        router.newRootScreen(FragmentScreen { PreviewFragment() })
    }

    override fun onCleared() {
        super.onCleared()
        repo.clear()
    }
}