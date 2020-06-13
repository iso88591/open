package grg.app.open.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.multidex.MultiDexApplication
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import grg.app.open.widgets.MeView

class MyApplication : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        app = this

        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                val decorView = activity.window.decorView
                if (decorView is ViewGroup) {
                    decorView.addView(MeView(MyApplication.context), MeView.layoutParams)
                }
            }

            override fun onActivityResumed(activity: Activity) {
            }

        })

    }

    companion object {
        lateinit var context: Context
        lateinit var app: MyApplication

        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                ClassicsHeader(context, null)
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                ClassicsFooter(context, null)
            }
        }

    }

}