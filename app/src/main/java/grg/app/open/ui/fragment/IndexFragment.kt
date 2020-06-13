package grg.app.open.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.youth.banner.Banner
import grg.app.open.R
import grg.app.open.adapter.IndexArticleAdapter
import grg.app.open.app.component.ObserverObservable
import grg.app.open.app.base.BaseFragment
import grg.app.open.app.base.SimpleDivideItemDec
import grg.app.open.app.component.bindToSmartRefreshLayout
import grg.app.open.model.MainModel
import grg.app.open.net.OOTransform
import grg.app.open.net.bean.IndexBanner
import grg.app.open.widgets.MeRecyclerView
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
    val mAdapter by lazy { IndexArticleAdapter() }
    val indexArticle by lazy {
        indexViewModel.getIndexArticle(Observer {
            it.datas?.let {
                mAdapter.addData(it)
            }
        }, {

        })
    }

    override fun onCreateView(savedInstanceState: Bundle?) {
        super.onCreateView(savedInstanceState)

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

        recyclerView.addItemDecoration(SimpleDivideItemDec {
            set(0, 0, 0, 1)
        })

        indexArticle.observe(this)

        indexArticle.bindToSmartRefreshLayout(refresh)

        indexArticle.begin()




    }

}