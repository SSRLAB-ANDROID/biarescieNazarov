package by.krokam.biarescie.mvvm.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.viewmodels.ListVM
import kotlinx.android.synthetic.main.fragment_list.*

abstract class ListFragment<T, VM : ListVM<T>> : BaseFragment<VM>(){
    override val contentLayoutID = R.layout.fragment_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = vm.adapter
        }
        swipeLayout.setOnRefreshListener {
            vm.loadData()
            swipeLayout.isRefreshing = false
        }
    }

}