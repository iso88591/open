package grg.app.open.ui.activity

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import grg.app.open.R
import grg.app.open.app.KEY_ARTICLE_URL
import grg.app.open.app.component.copyTextToClipBoard
import grg.app.open.app.extension.toast
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity(R.layout.activity_article_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView.bindLifeCycle(this.lifecycle)
        webView.simpleConfig()

        webView.settings.apply {

        }

        val article = intent.getStringExtra(KEY_ARTICLE_URL)

        tvUrl.text = article
        tvUrl.setOnClickListener {
            copyTextToClipBoard(tvUrl.text)
            toast("网址已经拷贝到剪贴板!")
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                view?.let {
                    progress.progress = newProgress
                }
            }
        }
        webView.loadUrl(article)


    }


}