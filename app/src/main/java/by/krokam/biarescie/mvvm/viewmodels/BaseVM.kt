package by.krokam.biarescie.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import by.krokam.biarescie.data.Repository2
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router

abstract class BaseVM : ViewModel() {
    val toolbarTitle = MutableLiveData<String>()
    private lateinit var mainVM : MainViewModel
    protected val subs = CompositeDisposable()

    protected val repo : Repository2
        get() = mainVM.repo

    protected val router : Router
        get() = mainVM.router

    fun setMainVM(mvm : MainViewModel){
        mainVM = mvm
        onCreate()
    }

    protected abstract fun onCreate()

    override fun onCleared() {
        super.onCleared()
        subs.clear()
    }
}