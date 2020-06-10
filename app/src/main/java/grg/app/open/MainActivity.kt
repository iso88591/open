package grg.app.open

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import grg.app.open.databinding.ActivityMainBinding
import grg.app.open.net.NetConfig
import grg.app.open.net.OOTransform
import grg.app.open.net.bean.Data
import grg.app.open.net.bean.IndexArticle

//import com.google.gson.Gson

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val model: MainModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentView =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        contentView.testModel = model

        contentView.tvTest.setOnClickListener {

            NetConfig.WanAndroid.get().indexArticleList(1)
                .observe(this, OOTransform(model.indexArticle) {
                    println("error======================:" + errorMsg)
                })

        }

    }

}