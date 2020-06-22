package grg.app.open.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import grg.app.open.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    //    private val model: MainModel by viewModels()
    private val mBottomNav by lazy(LazyThreadSafetyMode.NONE) { findViewById<BottomNavigationView>(R.id.bottomNav) }


    val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBottomNav.setupWithNavController(navController)

    }

}