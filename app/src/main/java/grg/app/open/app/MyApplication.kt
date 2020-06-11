package grg.app.open.app

import android.content.Context
import androidx.multidex.MultiDexApplication

class MyApplication : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        app = this

    }

    companion object {
        lateinit var context: Context
        lateinit var app: MyApplication
    }

}