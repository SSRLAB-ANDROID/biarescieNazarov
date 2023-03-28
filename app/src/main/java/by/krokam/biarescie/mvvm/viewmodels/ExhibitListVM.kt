package by.krokam.biarescie.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.mvvm.ui.ExhibitFragment
import by.krokam.biarescie.ui.recycler.ExhibitListAdapter
import by.krokam.biarescie.util.addTo
import by.krokam.biarescie.util.executeInBackSubOnMain
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ExhibitListVM : ListVM<Exhibit>() {
    val sectionID = MutableLiveData<Int>()
    override val adapter = ExhibitListAdapter().apply {
        onClick = {
            repo.selectedExhibitID = it.idPoint.toString()
            router.navigateTo(FragmentScreen { ExhibitFragment() })
        }
    }

    override fun onCreate() {
        repo.selectedSection.executeInBackSubOnMain().subscribe {
            adapter.name = it.name
            sectionID.value = it.id.toInt()
            isLoading.value = false
        }.addTo(subs)

        repo.exhibits.executeInBackSubOnMain().subscribe { it1 ->
            adapter.items = it1!!.sortedBy { it.id.toInt() }
            adapter.notifyDataSetChanged()
        }.addTo(subs)
    }
}