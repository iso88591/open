package grg.app.open.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import grg.app.open.R
import grg.app.open.app.MyApplication
import grg.app.open.databinding.VhIndexArticleBinding
import grg.app.open.net.bean.Data

class IndexArticleAdapter : PagedListAdapter<Data, IndexArticleAdapter.VH>(diff) {

    val inflater by lazy { LayoutInflater.from(MyApplication.context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexArticleAdapter.VH {
        return VH(parent)
    }

    override fun onBindViewHolder(holder: IndexArticleAdapter.VH, position: Int) {
        holder.binding?.data = getItem(position)
    }


    inner class VH(parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.vh_index_article, parent, false)) {

        val binding by lazy { DataBindingUtil.bind<VhIndexArticleBinding>(itemView) }

    }

    companion object {
        val diff by lazy {
            object : DiffUtil.ItemCallback<Data>() {
                override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }

                override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            }
        }
    }

}