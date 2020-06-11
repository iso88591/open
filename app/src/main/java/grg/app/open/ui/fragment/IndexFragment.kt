package grg.app.open.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import grg.app.open.R
import grg.app.open.adapter.IndexArticleAdapter
import grg.app.open.app.base.BaseFragment
import grg.app.open.model.MainModel

class IndexFragment : BaseFragment(R.layout.fragment_index) {

    private val recyclerView by lazy(LazyThreadSafetyMode.NONE) {
        mRootView.findViewById<RecyclerView>(
            R.id.recyclerView
        )
    }
    private val swipe by lazy(LazyThreadSafetyMode.NONE) {
        mRootView.findViewById<SwipeRefreshLayout>(
            R.id.swipe
        )
    }


    val indexViewModel by viewModels<MainModel>()
    val mAdapter by lazy { IndexArticleAdapter() }

    override fun onCreateView(savedInstanceState: Bundle?) {
        super.onCreateView(savedInstanceState)

//        swipe.setOnRefreshListener {
//
//        }
//        swipe.isEnabled = false

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        indexViewModel.indexArticle.observe(this, Observer {
            mAdapter.submitList(it)
        })


    }

}