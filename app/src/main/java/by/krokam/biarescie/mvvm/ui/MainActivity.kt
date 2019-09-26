package by.krokam.biarescie.mvvm.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import by.krokam.biarescie.R
import by.krokam.biarescie.app.App
import by.krokam.biarescie.mvvm.viewmodels.MainViewModel
import by.krokam.biarescie.navigation.Navigator
import by.krokam.biarescie.navigation.Screens
import by.krokam.biarescie.app.ContextWrapper
import by.krokam.biarescie.app.Language
import by.krokam.biarescie.app.LanguageManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationHolder {
    private val navigator = Navigator(this, R.id.container)
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setupNavigationMenu()
    }

    private fun setupNavigationMenu() {
        navigationView.apply {
            setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                drawerLayout.closeDrawers()
                when (menuItem.itemId) {
                    R.id.nav_lang -> ChooseLanguageDialog().show(supportFragmentManager, "lang")
                    R.id.nav_about -> { vm.router.navigateTo(Screens.ABOUT_US_SCREEN) }
                    R.id.nav_exit -> System.exit(0)
                }
                false
            }
        }
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    fun setLanguage(lang : Language){
        vm.language = lang
        recreate()
    }

    override fun attachBaseContext(newBase: Context) {
        val context = ContextWrapper.wrap(newBase, LanguageManager(App.INSTANSE!!).readLanguage().locale)
        super.attachBaseContext(context)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        vm.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        vm.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            vm.router.exit()
        }
    }
}
