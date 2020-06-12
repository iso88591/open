package grg.app.open.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.Banner
import grg.app.open.R
import grg.app.open.adapter.IndexArticleAdapter
import grg.app.open.app.ObserverObservable
import grg.app.open.app.base.BaseFragment
import grg.app.open.app.base.SimpleDivideItemDec
import grg.app.open.model.MainModel
import grg.app.open.net.OOTransform
import grg.app.open.net.bean.IndexBanner
import grg.app.open.widgets.MeRecyclerView
import grg.app.open.widgets.SimpleBannerImageLoader

class IndexFragment : BaseFragment(R.layout.fragment_index) {

    private val recyclerView by lazy(LazyThreadSafetyMode.NONE) {
        mRootView.findViewById<MeRecyclerView>(
            R.id.recyclerView
        )
    }

    val indexViewModel by viewModels<MainModel>()
    val mAdapter by lazy { IndexArticleAdapter() }

    override fun onCreateView(savedInstanceState: Bundle?) {
        super.onCreateView(savedInstanceState)

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        val view = layoutInflater.inflate(R.layout.vh_index_banner, recyclerView, false)
        mAdapter.addHeaderView(view)
        val banner = view.findViewById<Banner>(R.id.banner)
        banner.setImageLoader(SimpleBannerImageLoader)

        indexViewModel.indexBanner.observe(this@IndexFragment, OOTransform(ObserverObservable<List<IndexBanner>>{
            banner.update(it?.map { it.imagePath })
        }))

        recyclerView.addItemDecoration(SimpleDivideItemDec {
            set(0, 0, 0, 1)
        })

        indexViewModel.indexArticle(1).observe(this, OOTransform(mAdapter))


    }

}