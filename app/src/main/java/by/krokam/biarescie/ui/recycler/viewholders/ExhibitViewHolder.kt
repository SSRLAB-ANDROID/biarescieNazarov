package by.krokam.biarescie.ui.recycler.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Exhibit
import com.bumptech.glide.Glide

class ExhibitViewHolder(parent : ViewGroup) : BaseViewHolder<Exhibit>(parent, R.layout.item_exhibit){

    private lateinit var tvTitle: TextView
    private lateinit var tvName: TextView
    private lateinit var tvvol: ImageView
    private lateinit var photo: ImageView

    @SuppressLint("StringFormatMatches")
    override fun bind(item: Exhibit) {

        tvTitle = itemView.findViewById(R.id.tvTitle)
        tvName = itemView.findViewById(R.id.tvName)
        tvvol = itemView.findViewById(R.id.tvvol)
        photo = itemView.findViewById(R.id.photo)

        super.bind(item)
        itemView.run {
            tvTitle.text = context.getString(R.string.exhibit, item.idPoint)
            tvName.text = item.name
            Glide.with(context).load(item.logo).into(photo)
            if(item.sound.isNotEmpty())
            tvvol.visibility=View.VISIBLE
            else
                tvvol.visibility=View.GONE
        }
    }
}