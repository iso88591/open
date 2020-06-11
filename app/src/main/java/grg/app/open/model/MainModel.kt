package grg.app.open.model


import androidx.paging.toLiveData
import grg.app.open.app.ObservableViewModel
import grg.app.open.model.datasource.IndexArticleDataSourceFactory

class MainModel() : ObservableViewModel() {

    val indexArticle by lazy {
        IndexArticleDataSourceFactory().toLiveData(
            20,
            1
        )
    }

}