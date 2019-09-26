package by.krokam.biarescie.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.View.inflate
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.mvvm.ui.ExhibitFragment
import by.krokam.biarescie.mvvm.ui.MainActivity
import by.krokam.biarescie.player.PlayerController
import by.krokam.biarescie.player.PlayerView
import by.krokam.biarescie.util.addTo
import by.krokam.biarescie.util.executeInBackSubOnMain
import kotlinx.android.synthetic.main.fragment_exhibit.*

class ExhibitVM : BaseVM() {
    val selectedExhibit = MutableLiveData<Exhibit>()
    private val playerControllerImpl = PlayerController()
    val playerController
        get() = playerControllerImpl


    override fun onCreate() {
        repo.selectedExhibit.executeInBackSubOnMain().subscribe {
            selectedExhibit.value = it!!
            if(it.sound.length>0)
            playerControllerImpl.load(it.sound)
            else
                playerControllerImpl.clear()
        }.addTo(subs)
    }

    override fun onCleared() {
        super.onCleared()
        playerController.clear()
    }
}