package by.krokam.biarescie.ui.recycler.viewholders

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseViewHolder<T>(parent: ViewGroup, idLayout: Int) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(idLayout, parent, false)) {
    var onClick: ((item: T) -> Unit)? = null
    var item : T? = null

    init {
        itemView.setOnClickListener { item?.let { onClick?.invoke(it) } }
    }

    open fun bind(item: T) {
        this.item = item
    }
}