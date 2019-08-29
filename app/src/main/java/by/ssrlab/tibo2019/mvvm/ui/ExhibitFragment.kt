package by.krokam.biarescie.mvvm.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.viewmodels.ExhibitVM
import by.krokam.biarescie.ui.viewpager.CustomPagerAdapter
import by.krokam.biarescie.ui.viewpager.TextFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_exhibit.*

class ExhibitFragment : BaseFragment<ExhibitVM>() {
    override val contentLayoutID = R.layout.fragment_exhibit

    override fun initVM() {
        vm = ViewModelProviders.of(this).get(ExhibitVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupExhibitObserver()
        viewPlayer.attachController(vm.playerController)
    }


    private fun setupExhibitObserver() {
        vm.selectedExhibit.observe(this, Observer {
            Glide.with(context!!).load(it!!.photo).into(ivPhoto)
            ///vm.toolbarTitle.value =it.name //getString(R.string.exhibit, it.idPoint.toString())
            tvName.text = it.name
            tvPlace.text = it.pointMuseum

            pager.adapter = null

            pager.adapter = CustomPagerAdapter(childFragmentManager).apply {
                if (it.text.isNotBlank())
                    addFragment(
                            TextFragment().apply {
                                text = it.text
                                onHeigtChanged = {
                                    this@ExhibitFragment.pager?.apply {
                                        layoutParams.height = it
                                        requestLayout()
                                    }
                                }
                            },
                            ""
                    )
                else
                    if (it.textLong.isNotBlank() && it.textLong != it.text) {
                        addFragment(
                                TextFragment().apply {
                                    text = it.textLong
                                    onHeigtChanged = {
                                        this@ExhibitFragment.pager?.apply {
                                            layoutParams.height = it
                                            requestLayout()
                                        }
                                    }
                                },
                                ""
                        )
                    }
            }
            tabs.setupWithViewPager(pager)
        })
    }

}