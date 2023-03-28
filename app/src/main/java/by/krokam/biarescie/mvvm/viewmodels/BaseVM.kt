package by.krokam.biarescie.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.krokam.biarescie.data.Repository2
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable

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