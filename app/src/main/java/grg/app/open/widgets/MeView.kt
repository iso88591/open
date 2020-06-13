package grg.app.open.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

class MeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        isClickable = false
    }

    val str = "仅供个人学习研究 其他用途联系qq2658218247"

    val paint by lazy {
        Paint().apply {
            textSize = 35f
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        pointList.forEach {
            canvas?.apply {
                save()

                translate(it.x, it.y)
                rotate(-45f)
                drawText(str, 0, str.length, 0f, 0f, paint)
                restore()
            }
        }
    }

    val lineNum = 2
    val spanNum = 4
    var lineOffset = 0f
    var spanOffset = 0f
    val pointList = ArrayList<PointF>(lineNum * spanNum)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        lineOffset = width / lineNum.toFloat() * 0.8f
        spanOffset = height / spanNum.toFloat() * 1f

        for (line in 0 until lineNum) {
            for (span in 0 until spanNum) {

                pointList.add(
                    PointF(
                        width * (line + 1f) / lineNum - lineOffset,
                        height * (span + 1f) / spanNum - spanOffset * (if (line % 2 == 0) 0.5f else 0.3f)
                    )
                )

            }
        }

    }

    companion object {
        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

}