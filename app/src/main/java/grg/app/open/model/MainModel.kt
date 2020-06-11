package grg.app.open.model

import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedList
import androidx.paging.toLiveData
import grg.app.open.app.ObservableViewModel
import grg.app.open.app.ObserverObservable
import grg.app.open.model.datasource.IndexArticleDataSourceFactory
import grg.app.open.net.ApiWrapper
import grg.app.open.net.NetConfig
import grg.app.open.net.OOTransform
import grg.app.open.net.bean.Data
import grg.app.open.net.bean.IndexArticle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainModel() : ObservableViewModel() {

    val indexArticle by lazy {
        IndexArticleDataSourceFactory().toLiveData(
            20
        )
    }

}