package by.krokam.biarescie.ui.recycler.viewholders

import android.view.ViewGroup
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Section
import by.krokam.biarescie.util.isGone
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_section.view.*

class SectionViewHolder(parent: ViewGroup) : BaseViewHolder<Section>(parent, R.layout.item_section) {

    override fun bind(item: Section) {
        super.bind(item)
        itemView.apply {
            tvTitle.isGone(item.namePrefix.isEmpty())
            tvTitle.text = item.namePrefix
            tvName.text = item.name
            Glide.with(context).load(item.logo).into(photo)
        }
    }
}