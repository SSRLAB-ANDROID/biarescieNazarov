package by.krokam.biarescie.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import by.krokam.biarescie.ui.recycler.viewholders.BaseViewHolder

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    var onClick: ((item: T) -> Unit)? = null
    var items: List<T> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getViewHolder(parent, viewType).apply {
            onClick = this@BaseAdapter.onClick
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}