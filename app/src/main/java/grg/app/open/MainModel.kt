package grg.app.open

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import grg.app.open.net.ApiWrapper
import grg.app.open.net.bean.IndexArticle

class MainModel() : ObservableViewModel() {

    val indexArticle = ObserverObservable<IndexArticle?>()




}