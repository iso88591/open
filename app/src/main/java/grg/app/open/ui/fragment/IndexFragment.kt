package grg.app.open.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import grg.app.open.R
import grg.app.open.adapter.IndexArticleAdapter
import grg.app.open.app.base.BaseFragment
import grg.app.open.app.base.SimpleDivideItemDec
import grg.app.open.model.MainModel

class IndexFragment : BaseFragment(R.layout.fragment_index) {

    private val recyclerView by lazy(LazyThreadSafetyMode.NONE) {
        mRootView.findViewById<RecyclerView>(
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

        recyclerView.addItemDecoration(SimpleDivideItemDec {
            set(0, 0, 0, 1)
        })

        indexViewModel.indexArticle.observe(this, Observer {
            mAdapter.submitList(it)
        })


    }

}