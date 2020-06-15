package grg.app.open.ui.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import grg.app.open.R
import grg.app.open.app.component.open
import grg.app.open.ui.activity.MainActivity

class LoadingActivity : AppCompatActivity(R.layout.layout_loading) {

    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                open<MainActivity> {  }
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }.start()



    }

}