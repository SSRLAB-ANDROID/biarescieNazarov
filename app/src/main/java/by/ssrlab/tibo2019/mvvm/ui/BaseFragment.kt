package by.krokam.biarescie.mvvm.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.viewmodels.BaseVM
import by.krokam.biarescie.mvvm.viewmodels.MainViewModel
import by.krokam.biarescie.util.isGone
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*


abstract class BaseFragment<VM : BaseVM> : Fragment() {
    protected abstract val contentLayoutID: Int
    protected open val isToolbarVisible = true

    protected lateinit var mainVM: MainViewModel
    protected val subscriptions = CompositeDisposable()

    protected lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVM = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        initVM()
        vm.setMainVM(mainVM)
    }

    abstract fun initVM()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base, container, false).apply {
            toolbar.apply {
                activity!!.let { act ->
                    (act as AppCompatActivity).setSupportActionBar(this)
                    setNavigationOnClickListener {     activity!!.onBackPressed() }
                }
                isGone(!isToolbarVisible)
            }
            inflater.inflate(contentLayoutID, contentHolder, true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.toolbarTitle.observe(this, Observer {
            toolbar.tvTitle.text = it!!
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}