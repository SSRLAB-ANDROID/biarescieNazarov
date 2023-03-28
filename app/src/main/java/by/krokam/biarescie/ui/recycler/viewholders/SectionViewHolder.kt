package by.krokam.biarescie.ui.recycler.viewholders

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Section
import by.krokam.biarescie.util.isGone
import com.bumptech.glide.Glide

class SectionViewHolder(parent: ViewGroup) : BaseViewHolder<Section>(parent, R.layout.item_section) {

    private lateinit var tvTitle: TextView
    private lateinit var tvName: TextView
    private lateinit var photo: ImageView

    override fun bind(item: Section) {
        super.bind(item)

        tvTitle = itemView.findViewById(R.id.tvTitle)
        tvName = itemView.findViewById(R.id.tvName)
        photo = itemView.findViewById(R.id.photo)

        itemView.apply {
            tvTitle.isGone(item.namePrefix.isEmpty())
            tvTitle.text = item.namePrefix
            tvName.text = item.name
            Glide.with(context).load(item.logo).into(photo)
        }
    }
}