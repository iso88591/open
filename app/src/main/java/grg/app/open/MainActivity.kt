package grg.app.open

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer

//import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private val model: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.selected.observe(this, Observer {
            println(it)
        })

//        Gson
//        Gson

        /**
         * 数据绑定
        以声明方式将可观察数据绑定到界面元素

        Lifecycles
        管理您的 Activity 和 Fragment 生命周期

        LiveData
        在底层数据库更改时通知视图


        Navigation
        处理应用内导航所需的一切

        Paging
        逐步从您的数据源按需加载信息

        Room
        流畅地访问 SQLite 数据库

        ViewModel
        以注重生命周期的方式管理界面相关的数据

        WorkManager
        管理您的 Android 后台作业
         */


    }
}