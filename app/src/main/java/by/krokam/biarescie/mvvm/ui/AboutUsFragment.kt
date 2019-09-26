package by.krokam.biarescie.mvvm.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Html
import android.view.View
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.viewmodels.AboutUsVM
import kotlinx.android.synthetic.main.fragment_about.*

class AboutUsFragment : BaseFragment<AboutUsVM>(){
    override val contentLayoutID = R.layout.fragment_about

    override fun initVM() {
        vm = ViewModelProviders.of(this).get(AboutUsVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = Html.fromHtml(resources.getString(R.string.about_us_text))
    }


}