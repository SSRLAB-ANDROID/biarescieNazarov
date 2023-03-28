package by.krokam.biarescie.mvvm.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.mvvm.viewmodels.ExhibitListVM

class ExhibitListFragment : ListFragment<Exhibit, ExhibitListVM>(){

    override fun initVM() {
        vm = ViewModelProvider(this)[ExhibitListVM::class.java]
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.sectionID.observe(viewLifecycleOwner, Observer {
            if(mainVM.repo.selectedSection.value!!.namePrefix.isNotEmpty())
            vm.toolbarTitle.value = mainVM.repo.selectedSection.value!!.namePrefix //getString(R.string.section, it!!.toString())
        })
    }

}