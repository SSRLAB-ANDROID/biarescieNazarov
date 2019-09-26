package by.krokam.biarescie.ui.recycler

import android.view.ViewGroup
import by.krokam.biarescie.data.items.Section
import by.krokam.biarescie.ui.recycler.viewholders.BaseViewHolder
import by.krokam.biarescie.ui.recycler.viewholders.SectionViewHolder

class SectionListAdapter : BaseAdapter<Section>(){

    override fun getViewHolder(parent: ViewGroup, viewType : Int): BaseViewHolder<Section> {
        return SectionViewHolder(parent)
    }
}