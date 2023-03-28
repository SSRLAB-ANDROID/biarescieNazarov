package by.krokam.biarescie.mvvm.ui

import android.content.Context
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.krokam.biarescie.R
import by.krokam.biarescie.app.App
import by.krokam.biarescie.mvvm.viewmodels.MainViewModel
import by.krokam.biarescie.navigation.Navigator
import by.krokam.biarescie.app.ContextWrapper
import by.krokam.biarescie.app.Language
import by.krokam.biarescie.app.LanguageManager
import by.krokam.biarescie.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), NavigationHolder {

    private val navigator = Navigator(this, R.id.container)
    private lateinit var vm: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[MainViewModel::class.java]
        setupNavigationMenu()
    }

    private fun setupNavigationMenu() {
        binding.navigationView.apply {
            setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                binding.drawerLayout.closeDrawers()
                when (menuItem.itemId) {
                    R.id.nav_lang -> ChooseLanguageDialog().show(supportFragmentManager, "lang")
                    R.id.nav_about -> { vm.router.navigateTo(FragmentScreen { AboutUsFragment() }) }
                    R.id.nav_exit -> exitProcess(0)
                }
                false
            }
        }
    }

    override fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers()
        } else {
            vm.router.exit()
        }
    }
}
