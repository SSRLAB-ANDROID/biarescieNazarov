package by.krokam.biarescie.mvvm.viewmodels

import by.krokam.biarescie.data.items.Section
import by.krokam.biarescie.mvvm.ui.ExhibitListFragment
import by.krokam.biarescie.ui.recycler.SectionListAdapter
import by.krokam.biarescie.util.addTo
import by.krokam.biarescie.util.executeInBackSubOnMain
import com.github.terrakok.cicerone.androidx.FragmentScreen

class SectionListVM : ListVM<Section>() {

    override val adapter = SectionListAdapter().apply {
        onClick = {
            repo.selectedSectionID = it.id
            router.navigateTo(FragmentScreen { ExhibitListFragment() })
        }
    }

    override fun onCreate() {
        repo.sections.executeInBackSubOnMain().subscribe { it1 ->
            adapter.items = it1!!.sortedBy { it.id.toInt() }
            isLoading.value = false

        }.addTo(subs)
    }

}