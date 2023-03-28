package by.krokam.biarescie.mvvm.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Section
import by.krokam.biarescie.mvvm.viewmodels.SectionListVM

class SectionListFragment : ListFragment<Section, SectionListVM>(){

    override fun initVM() {
        vm = ViewModelProvider(this)[SectionListVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_menu)
            setNavigationOnClickListener {
                (requireActivity() as NavigationHolder).openDrawer()
            }
        }

        if(!mainVM.isInited){
            ChooseLanguageDialog().show(childFragmentManager, "lang")
            mainVM.isInited = true
        }
    }

}