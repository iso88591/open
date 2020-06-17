package grg.app.open.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.dynamicfeatures.Constants
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import grg.app.open.R
import grg.app.open.app.ACTION_LOGIN
import grg.app.open.app.ACTION_LOGOUT
import grg.app.open.app.component.bus.post
import grg.app.open.app.component.bus.subscribe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

//    private val model: MainModel by viewModels()


    val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottomNav.setupWithNavController(navController)



    }

}