package by.krokam.biarescie.ui.recycler.viewholders

import android.view.View
import android.view.ViewGroup
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Exhibit
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_exhibit.view.*
import kotlinx.android.synthetic.main.item_section.view.*
import kotlinx.android.synthetic.main.item_section.view.photo
import kotlinx.android.synthetic.main.item_section.view.tvName
import kotlinx.android.synthetic.main.item_section.view.tvTitle

class ExhibitViewHolder(parent : ViewGroup) : BaseViewHolder<Exhibit>(parent, R.layout.item_exhibit){

    override fun bind(item: Exhibit) {
        super.bind(item)
        itemView.run {
            tvTitle.text = context.getString(R.string.exhibit,item.idPoint)
            tvName.text = item.name
            Glide.with(context).load(item.logo).into(photo)
            if(item.sound.length!=0)
            tvvol.visibility=View.VISIBLE
            else
                tvvol.visibility=View.GONE
        }
    }
}