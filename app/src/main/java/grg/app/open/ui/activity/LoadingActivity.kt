package grg.app.open.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import grg.app.open.R
import grg.app.open.app.component.open

class LoadingActivity : AppCompatActivity(R.layout.layout_loading) {


    private val motionLayout by lazy(LazyThreadSafetyMode.NONE) { findViewById<MotionLayout>(R.id.motionLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        motionLayout.apply {
            setTransition(R.id.start,R.id.end)

            transitionToEnd()

            setTransitionListener(object : MotionLayout.TransitionListener{
                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {
                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    open<MainActivity> {  }
                    finish()
                }


            })

        }



    }

}