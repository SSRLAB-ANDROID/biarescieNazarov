package by.krokam.biarescie.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import by.krokam.biarescie.ui.recycler.BaseAdapter

abstract class ListVM<T>() : BaseVM(){
    abstract val adapter : BaseAdapter<T>

    val isLoading = MutableLiveData<Boolean>()

    open fun loadData(){
        repo.loadData()
    }
}