package by.krokam.biarescie.mvvm.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Section
import by.krokam.biarescie.mvvm.viewmodels.SectionListVM
import kotlinx.android.synthetic.main.fragment_base.*

class SectionListFragment : ListFragment<Section, SectionListVM>(){


    override fun initVM() {
        vm = ViewModelProviders.of(this).get(SectionListVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_menu)
            setNavigationOnClickListener {
                (activity!! as NavigationHolder).openDrawer()
            }
        }

        if(!mainVM.isInited){
            ChooseLanguageDialog().show(childFragmentManager, "lang")
            mainVM.isInited = true
        }
    }

}