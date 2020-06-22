package grg.app.open.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.youth.banner.Banner
import grg.app.open.R
import grg.app.open.adapter.IndexArticleAdapter
import grg.app.open.app.ACTION_LOGIN
import grg.app.open.app.ACTION_LOGOUT
import grg.app.open.app.KEY_ARTICLE_URL
import grg.app.open.app.component.ObserverObservable
import grg.app.open.app.base.BaseFragment
import grg.app.open.app.base.SimpleDivideItemDec
import grg.app.open.app.base.divideItemDec
import grg.app.open.app.base.simpleDivideItemDec
import grg.app.open.app.component.PageLiveDataList
import grg.app.open.app.component.bindToSmartRefreshLayout
import grg.app.open.app.component.bus.post
import grg.app.open.app.component.open
import grg.app.open.model.MainModel
import grg.app.open.net.OOTransform
import grg.app.open.net.bean.IndexArticle
import grg.app.open.net.bean.IndexBanner
import grg.app.open.ui.activity.ArticleDetailActivity
import grg.app.open.widgets.SimpleBannerImageLoader

class IndexFragment : BaseFragment(R.layout.fragment_index) {

    private val recyclerView by lazy(LazyThreadSafetyMode.NONE) {
        mRootView.findViewById<RecyclerView>(
            R.id.recyclerView
        )
    }
    private val refresh by lazy(LazyThreadSafetyMode.NONE) {
        mRootView.findViewById<SmartRefreshLayout>(
            R.id.refresh
        )
    }

    val indexViewModel by viewModels<MainModel>()
    val mAdapter by lazy {
        IndexArticleAdapter()
    }

    val indexArticle: PageLiveDataList<IndexArticle> by lazy {
        indexViewModel.getIndexArticle(Observer {
            it.datas?.let {
                if (indexArticle.lastOperator == PageLiveDataList.OPERATOR_REFRESH) {
                    mAdapter.setNewInstance(ArrayList(it))
                } else {

                    mAdapter.addData(it)
                }
            }
        }, {


        })
    }

    override fun onCreateView(savedInstanceState: Bundle?) {
        super.onCreateView(savedInstanceState)

        mAdapter.setOnItemClickListener { adapter, view, position ->
            val item = mAdapter.getItem(position)
            mActivity?.open<ArticleDetailActivity> {
                putExtra(KEY_ARTICLE_URL, item.link)
            }
        }

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            setHasFixedSize(true)
        }

        val view = layoutInflater.inflate(R.layout.vh_index_banner, recyclerView, false)
        mAdapter.addHeaderView(view)
        val banner = view.findViewById<Banner>(R.id.banner)
        banner.setImageLoader(SimpleBannerImageLoader)

        indexViewModel.indexBanner.observe(
            this@IndexFragment,
            OOTransform(ObserverObservable<List<IndexBanner>> {
                banner.update(it?.map { it.imagePath })
            })
        )

        recyclerView.simpleDivideItemDec {
            set(0, 0, 0, 1)
        }


        recyclerView.divideItemDec { rect, view, recyclerView, state ->
            println("recyclerView滑动了="+recyclerView.scrollY)
        }


        indexArticle.observe(this)

        indexArticle.bindToSmartRefreshLayout(refresh)

        indexArticle.begin()


    }

}