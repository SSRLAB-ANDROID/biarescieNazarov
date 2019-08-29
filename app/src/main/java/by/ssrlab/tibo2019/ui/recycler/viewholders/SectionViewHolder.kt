package by.krokam.biarescie.ui.recycler.viewholders

import android.view.ViewGroup
import by.krokam.biarescie.R
import by.krokam.biarescie.data.items.Section
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_section.view.*

class SectionViewHolder(parent : ViewGroup) : BaseViewHolder<Section>(parent, R.layout.item_section){

    override fun bind(item: Section) {
        super.bind(item)
        itemView.run {

          /*  if(item.name.equals("Археалагічны музей Бярэсце")||item.name.equals("Раскоп")||item.name.equals("РАСКОП (паўднёвы бок)")
                    ||item.name.equals("Археологический музей Берестье")||item.name.equals("РАСКОП (южная сторона)") ){
                tvTitle.text = ""
                tvName.textSize= 18F
            }
            else{
            tvTitle.text = context.getString((R.string.section),(item.id.toInt()-2).toString())
            }*/
            tvTitle.text=item.namePrefix
            tvName.text = item.name
            Glide.with(context).load(item.logo).into(photo)
        }
    }
}