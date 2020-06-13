package grg.app.open.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import grg.app.open.app.component.ObservableViewModel
import grg.app.open.app.component.PageLiveDataList
import grg.app.open.net.ApiWrapper
import grg.app.open.net.NetConfig
import grg.app.open.net.WanAndroidDispatcherTransform
import grg.app.open.net.bean.IndexArticle

class MainModel() : ObservableViewModel() {

    private fun indexArticle(page: Int) = NetConfig.WanAndroid.get().indexArticleList(page)

    val indexBanner = NetConfig.WanAndroid.get().indexBanner()

    fun getIndexArticle(
        dr: Observer<IndexArticle>,
        errorCallback: (ApiWrapper<IndexArticle>) -> Unit
    ) =
        PageLiveDataList<IndexArticle>().apply {
            ds = { i: Int, function: (nextPage: Int, t: LiveData<IndexArticle>?) -> Unit ->

                val indexArticle = indexArticle(nowPage)

                val map = indexArticle.map {
                    var transform =
                        WanAndroidDispatcherTransform.transform<IndexArticle>(it, errorCallback)
                    transform ?: IndexArticle()
                }

                function.invoke(nowPage + 1, map)
            }

            dataResult = dr
        }


}