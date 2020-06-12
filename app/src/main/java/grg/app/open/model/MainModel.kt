package grg.app.open.model


import androidx.paging.toLiveData
import grg.app.open.app.ObservableViewModel
import grg.app.open.net.NetConfig

class MainModel() : ObservableViewModel() {

    fun indexArticle(page: Int) = NetConfig.WanAndroid.get().indexArticleList(page)

    val indexBanner = NetConfig.WanAndroid.get().indexBanner()


}