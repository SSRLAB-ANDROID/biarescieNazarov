package by.krokam.biarescie.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.ui.*
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command

class Navigator(private val activity: AppCompatActivity, containerID: Int) :
    SupportFragmentNavigator(activity.supportFragmentManager, containerID) {

    override fun exit() {
        activity.finish()
    }

    override fun showSystemMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        return when (screenKey) {
            Screens.SECTION_LIST_SCREEN -> SectionListFragment()
            Screens.EXHIBIT_LIST_SCREEN -> ExhibitListFragment()
            Screens.EXHIBIT_SCREEN -> ExhibitFragment()
            Screens.ABOUT_US_SCREEN -> AboutUsFragment()
            Screens.PREVIEW_SCREEN -> PreviewFragment()
            else -> Fragment()
        }
    }

    override fun setupFragmentTransactionAnimation(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment,
        fragmentTransaction: FragmentTransaction
    ) {
        currentFragment?.let {
            fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
        }

    }
}
