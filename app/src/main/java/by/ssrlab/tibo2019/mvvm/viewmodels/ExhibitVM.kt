package by.krokam.biarescie.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.mvvm.ui.ExhibitFragment
import by.krokam.biarescie.player.PlayerController
import by.krokam.biarescie.player.PlayerView
import by.krokam.biarescie.util.addTo
import by.krokam.biarescie.util.executeInBackSubOnMain

class ExhibitVM : BaseVM() {
    val selectedExhibit = MutableLiveData<Exhibit>()
    private val playerControllerImpl = PlayerController()
    val playerController
        get() = playerControllerImpl


    override fun onCreate() {
        repo.selectedExhibit.executeInBackSubOnMain().subscribe {
            selectedExhibit.value = it!!
            if(!(it.sound).equals(""))
            playerControllerImpl.load(it.sound)
            else {

                playerControllerImpl.clear()
            }
        }.addTo(subs)
    }

    override fun onCleared() {
        super.onCleared()
        playerController.clear()
    }
}