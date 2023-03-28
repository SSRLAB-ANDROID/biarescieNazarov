package by.krokam.biarescie.navigation

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.ui.*
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator

class Navigator(activity: MainActivity, containerID: Int) :
    AppNavigator(activity, containerID) {

    fun exit() {
        activity.finish()
    }

    fun showSystemMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun createFragment(screenKey: String?, data: Any?): Fragment {
        return when (screenKey) {
            Screens.SECTION_LIST_SCREEN -> SectionListFragment()
            Screens.EXHIBIT_LIST_SCREEN -> ExhibitListFragment()
            Screens.EXHIBIT_SCREEN -> ExhibitFragment()
            Screens.ABOUT_US_SCREEN -> AboutUsFragment()
            Screens.PREVIEW_SCREEN -> PreviewFragment()
            else -> Fragment()
        }
    }

    fun setupFragmentTransactionAnimation(
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
