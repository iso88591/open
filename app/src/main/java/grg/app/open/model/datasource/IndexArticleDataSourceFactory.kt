package grg.app.open.model.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import grg.app.open.net.NetConfig
import grg.app.open.net.bean.Data
import grg.app.open.net.bean.IndexArticle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IndexArticleDataSourceFactory : DataSource.Factory<Int, Data>() {

    override fun create(): DataSource<Int, Data> {
        return IndexArticleDataSource()
    }

    fun request(page: Int, dataCallback: ((List<Data>, Int) -> Unit)) {
        val indexArticleList = NetConfig.WanAndroid.get().indexArticleList(page)

        indexArticleList
            .subscribe { t1, t2 ->
                t1?.let {
                    if (t1.errorCode >= 0) {
                        t1.data?.datas?.also {
                            dataCallback.invoke(it, page + 1)
                        }
                    }
                }
            }

    }

    //
    inner class IndexArticleDataSource : PageKeyedDataSource<Int, Data>() {
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Data>
        ) {

            request(1) { list, i ->
                callback.onResult(list, null, 2)
            }

        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
            request(params.key) { list, i ->
                callback.onResult(list, i)
            }
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Data>
        ) {
        }


    }

}