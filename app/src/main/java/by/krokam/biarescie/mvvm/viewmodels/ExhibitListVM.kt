package by.krokam.biarescie.mvvm.viewmodels

import android.arch.lifecycle.MutableLiveData
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.navigation.Screens
import by.krokam.biarescie.ui.recycler.ExhibitListAdapter
import by.krokam.biarescie.util.addTo
import by.krokam.biarescie.util.executeInBackSubOnMain

class ExhibitListVM : ListVM<Exhibit>(){
    val sectionID = MutableLiveData<Int>()
    override val adapter = ExhibitListAdapter().apply {
        onClick = {
            repo.selectedExhibitID = it.idPoint.toString()
            router.navigateTo(Screens.EXHIBIT_SCREEN)
        }
    }

    override fun onCreate() {
        repo.selectedSection.executeInBackSubOnMain().subscribe {
            adapter.name = it.name
            sectionID.value = it.id.toInt()
            isLoading.value = false
        }.addTo(subs)

        repo.exhibits.executeInBackSubOnMain().subscribe {
            adapter.items = it!!.sortedBy { it.id.toInt() }
            adapter.notifyDataSetChanged()
        }.addTo(subs)
    }
}