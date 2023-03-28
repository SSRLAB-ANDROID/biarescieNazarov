package by.krokam.biarescie.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.viewmodels.BaseVM
import by.krokam.biarescie.mvvm.viewmodels.MainViewModel
import by.krokam.biarescie.util.isGone
import by.krokam.biarescie.util.mainViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<VM : BaseVM> : Fragment() {

    protected abstract val contentLayoutID: Int
    protected open val isToolbarVisible = true

    open lateinit var mainVM: MainViewModel
    private val subscriptions = CompositeDisposable()

    lateinit var toolbar: Toolbar
    private lateinit var tvTitle: TextView
    private lateinit var contentHolder: FrameLayout

    protected lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVM = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel = mainVM
        initVM()
        vm.setMainVM(mainVM)
    }

    abstract fun initVM()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base, container, false).apply {
            toolbar = findViewById(R.id.toolbar)
            contentHolder = findViewById(R.id.contentHolder)
            tvTitle = findViewById(R.id.tvTitle)
            toolbar.apply {
                requireActivity().let { act ->
                    (act as AppCompatActivity).setSupportActionBar(this)
                    setNavigationOnClickListener { requireActivity().onBackPressed() }
                }
                isGone(!isToolbarVisible)
            }
            inflater.inflate(contentLayoutID, contentHolder, true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.toolbarTitle.observe(viewLifecycleOwner, Observer {
            tvTitle.text = it!!
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}