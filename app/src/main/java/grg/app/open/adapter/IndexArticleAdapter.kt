package grg.app.open.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import grg.app.open.R
import grg.app.open.app.MyApplication
import grg.app.open.databinding.VhIndexArticleBinding
import grg.app.open.net.bean.Data
import java.util.*

class IndexArticleAdapter : RecyclerView.Adapter<IndexArticleAdapter.VH>() {

    private val list by lazy { ArrayList<Data>() }
    val inflater by lazy { LayoutInflater.from(MyApplication.context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexArticleAdapter.VH {
        return VH(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: IndexArticleAdapter.VH, position: Int) {
        holder.binding?.data = list[position]
    }

    fun addData(dataList: List<Data>) {
        list.addAll(dataList)
        if (dataList.size == list.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(list.size - dataList.size, list.size)
        }
    }

    inner class VH(parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.vh_index_article, parent, false)) {

        val binding by lazy { DataBindingUtil.bind<VhIndexArticleBinding>(itemView) }

    }

}