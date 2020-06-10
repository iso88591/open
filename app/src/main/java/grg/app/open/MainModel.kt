package grg.app.open

import grg.app.open.app.ObservableViewModel
import grg.app.open.app.ObserverObservable
import grg.app.open.net.bean.IndexArticle

class MainModel() : ObservableViewModel() {

    val indexArticle = ObserverObservable<IndexArticle?>()




}