package grg.app.open.app.component

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import grg.app.open.app.component.PageLiveDataList.Companion.OPERATOR_LOADMORE
import grg.app.open.app.component.PageLiveDataList.Companion.OPERATOR_REFRESH

class PageLiveDataList<T> : LiveData<LiveData<T>>() {

    private var page = 0

    var lastOperator: Int = OPERATOR_REFRESH
    private var willResetPage = false
    var ds:
            (
                (
                nowPage: Int,
                callBack: (nextPage: Int, t: LiveData<T>?) -> Unit
            ) -> Unit
            )? = null

    var dataResult: Observer<T>? = null

    val nowPage: Int
        get() {
            if (willResetPage) {
                page = 1
                willResetPage = false
            }
            return page
        }

    fun observe(owner: LifecycleOwner) {
        super.observe(owner, Observer<LiveData<T>> {
            dataResult?.run {
                it.observe(owner, this)
            }
        })
    }

    /**
     * 重置 page
     */
    fun resetPage() {
        willResetPage = true
    }

    fun begin() {
        checkNotNull(ds) { "必须要设置DataSource" }

        lastOperator = OPERATOR_REFRESH

        resetPage()

        requestData()

    }

    fun nextPage() {
        checkNotNull(ds) { "必须要设置DataSource" }

        lastOperator = OPERATOR_LOADMORE

        requestData()

    }

    private fun requestData() {
        ds!!.let { ds ->
            ds.invoke(nowPage, ::callBack)
        }
    }

    private fun callBack(nextPage: Int, liveData: LiveData<T>?) {
        page = nextPage
        postValue(liveData)
    }

    companion object {
        const val OPERATOR_REFRESH = 0
        const val OPERATOR_LOADMORE = 1
    }

}

fun <T> PageLiveDataList<T>.bindToSmartRefreshLayout(smartRefreshLayout: SmartRefreshLayout) {
    smartRefreshLayout.setOnRefreshListener {
        this.begin()
    }

    smartRefreshLayout.setOnLoadMoreListener {
        this.nextPage()
    }
    val temp = this.dataResult
    this.dataResult = Observer<T> {

        when (lastOperator) {
            OPERATOR_REFRESH -> {
                smartRefreshLayout.finishRefresh()
            }
            OPERATOR_LOADMORE -> {
                smartRefreshLayout.finishLoadMore()
            }
            else -> {

            }
        }

        temp?.onChanged(it)
    }

}