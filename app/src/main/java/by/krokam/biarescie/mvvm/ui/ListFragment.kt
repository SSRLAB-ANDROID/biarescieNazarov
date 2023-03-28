package by.krokam.biarescie.mvvm.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.krokam.biarescie.R
import by.krokam.biarescie.mvvm.viewmodels.ListVM

abstract class ListFragment<T, VM : ListVM<T>> : BaseFragment<VM>(){

    override val contentLayoutID = R.layout.fragment_list
    private lateinit var recycler: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.recycler)
        swipeLayout = view.findViewById(R.id.swipeLayout)

        super.onViewCreated(view, savedInstanceState)
        recycler.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = vm.adapter
        }
        swipeLayout.setOnRefreshListener {
            vm.loadData()
            swipeLayout.isRefreshing = false
        }
    }

}