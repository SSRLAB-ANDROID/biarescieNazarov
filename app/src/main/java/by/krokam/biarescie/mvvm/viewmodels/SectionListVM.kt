package by.krokam.biarescie.mvvm.viewmodels

import by.krokam.biarescie.data.items.Section
import by.krokam.biarescie.navigation.Screens
import by.krokam.biarescie.ui.recycler.SectionListAdapter
import by.krokam.biarescie.util.addTo
import by.krokam.biarescie.util.executeInBackSubOnMain

class SectionListVM : ListVM<Section>() {

    override val adapter = SectionListAdapter().apply {
        onClick = {
            repo.selectedSectionID = it.id
            router.navigateTo(Screens.EXHIBIT_LIST_SCREEN)
        }
    }

    override fun onCreate() {
        repo.sections.executeInBackSubOnMain().subscribe {
            adapter.items = it!!.sortedBy { it.id.toInt() }
            isLoading.value = false

        }.addTo(subs)
    }

}