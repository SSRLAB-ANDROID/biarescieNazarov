package by.krokam.biarescie.ui.recycler.viewholders

import android.view.View
import android.view.ViewGroup
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Section
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_section.view.*

class SectionViewHolder(parent : ViewGroup) : BaseViewHolder<Section>(parent, R.layout.item_section){

    override fun bind(item: Section) {
        super.bind(item)
        itemView.run {
            if(item.namePrefix.length!=0)
            tvTitle.text=item.namePrefix
            else
                tvTitle.visibility= View.GONE
            tvName.text = item.name
            Glide.with(context).load(item.logo).into(photo)
        }
    }
}