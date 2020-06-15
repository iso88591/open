package grg.app.open.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import grg.app.open.R
import grg.app.open.app.KEY_ARTICLE_URL
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity(R.layout.activity_article_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView.bindLifeCycle(this.lifecycle)
        webView.simpleConfig()

        webView.settings.apply {

        }

        val article = intent.getStringExtra(KEY_ARTICLE_URL)

        webView.loadUrl(article)


    }


}