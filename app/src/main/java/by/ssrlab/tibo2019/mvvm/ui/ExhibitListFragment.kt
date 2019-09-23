package by.krokam.biarescie.mvvm.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import by.krokam.biarescie.R
import by.krokam.biarescie.data.Repository2
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.data.items.Section
import by.krokam.biarescie.mvvm.viewmodels.ExhibitListVM

class ExhibitListFragment : ListFragment<Exhibit, ExhibitListVM>(){

    override fun initVM() {
        vm = ViewModelProviders.of(this).get(ExhibitListVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.sectionID.observe(this, Observer {
            if(mainVM.repo.selectedSection.value!!.namePrefix.length>0)
            vm.toolbarTitle.value =mainVM.repo.selectedSection.value!!.namePrefix //getString(R.string.section, it!!.toString())


        })
    }

}