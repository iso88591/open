package grg.app.open.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


class LifeCycleWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    fun bindLifeCycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(LifecycleEventObserver { source: LifecycleOwner, event: Lifecycle.Event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {

                }
                Lifecycle.Event.ON_START -> {
                }
                Lifecycle.Event.ON_RESUME -> {
                    onResume()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    onPause()
                }
                Lifecycle.Event.ON_STOP -> {
                }
                Lifecycle.Event.ON_DESTROY -> {
                    //mWebView.clearHistory();
                    //        mWebView.clearCache(true);
                    //        mWebView.loadUrl("about:blank"); // clearView() should be changed to loadUrl("about:blank"), since clearView() is deprecated now
                    //        mWebView.freeMemory();
                    //        mWebView.pauseTimers();
                    clearHistory()
                    clearCache(true)
                    loadUrl("about:blank")
                    freeMemory()
                    pauseTimers()
                    destroy()

                    if (parent is ViewGroup) {
                        (parent as ViewGroup).removeView(this)
                    }
                }
                Lifecycle.Event.ON_ANY -> {
                }
            }
        })
    }

    fun simpleConfig() {

        with(settings) {
            setSupportZoom(false)
            useWideViewPort = true
            loadWithOverviewMode = true
            defaultTextEncodingName = "utf-8"
            loadsImagesAutomatically = true
            javaScriptEnabled = true
            supportMultipleWindows()
        }

//
////支持缩放，默认为true。
////支持缩放，默认为true。
//        webSettings.setSupportZoom(false)
////调整图片至适合webview的大小
////调整图片至适合webview的大小
//        webSettings.useWideViewPort = true
//// 缩放至屏幕的大小
//// 缩放至屏幕的大小
//        webSettings.loadWithOverviewMode = true
////设置默认编码
////设置默认编码
//        webSettings.defaultTextEncodingName = "utf-8"
//////设置自动加载图片
//////设置自动加载图片
//        webSettings.loadsImagesAutomatically = true
//
//        //多窗口
//        supportMultipleWindows();
////获取触摸焦点
//        webview.requestFocusFromTouch();
////允许访问文件
//        setAllowFileAccess(true);
////开启javascript
//        setJavaScriptEnabled(true);
//        //支持通过JS打开新窗口
//        setJavaScriptCanOpenWindowsAutomatically(true);
////提高渲染的优先级
//        webSettings.setRenderPriority(RenderPriority.HIGH);
//        //支持内容重新布局
//        setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
////关闭webview中缓存
//        setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    }


}