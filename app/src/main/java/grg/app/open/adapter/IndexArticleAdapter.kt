package grg.app.open.adapter

import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import grg.app.open.R
import grg.app.open.app.ObserverObservable
import grg.app.open.app.extension.databinding
import grg.app.open.databinding.VhIndexArticleBinding
import grg.app.open.net.bean.Data
import grg.app.open.net.bean.IndexArticle

class IndexArticleAdapter : BaseQuickAdapter<Data, BaseViewHolder>(R.layout.vh_index_article),
    Observer<IndexArticle> {


    override fun convert(holder: BaseViewHolder, item: Data) {
        holder.itemView.databinding<VhIndexArticleBinding>().data = item
    }

    override fun onChanged(t: IndexArticle) {
        addData(t!!.datas!!)
    }

}