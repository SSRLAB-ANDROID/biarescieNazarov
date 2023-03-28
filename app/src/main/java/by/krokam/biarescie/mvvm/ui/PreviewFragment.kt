package by.krokam.biarescie.mvvm.ui

import androidx.lifecycle.ViewModelProvider
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.viewmodels.PreviewVM

class PreviewFragment : BaseFragment<PreviewVM>(){
    override val isToolbarVisible = false
    override val contentLayoutID = R.layout.fragment_preview

    override fun initVM() {
        vm = ViewModelProvider(this)[PreviewVM::class.java]
    }
}