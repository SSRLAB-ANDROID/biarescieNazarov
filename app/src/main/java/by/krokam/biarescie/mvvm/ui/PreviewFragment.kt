package by.krokam.biarescie.mvvm.ui

import android.arch.lifecycle.ViewModelProviders
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.viewmodels.PreviewVM

class PreviewFragment : BaseFragment<PreviewVM>(){
    override val isToolbarVisible = false
    override val contentLayoutID = R.layout.fragment_preview

    override fun initVM() {
        vm = ViewModelProviders.of(this).get(PreviewVM::class.java)
    }
}