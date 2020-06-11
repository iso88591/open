package grg.app.open.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class MeRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val str = "龚任根的app 盗用必究"

    val paint by lazy {
        Paint().apply {
            textSize = 50f
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        caculPoints().forEach {
            canvas?.apply {
                save()
                translate(it.x, it.y)
                rotate(-45f)
                drawText(str, 0, str.length, 0f, 0f, paint)
                restore()
            }
        }
    }

    fun caculPoints(): List<PointF> {
        val halfWidth = width / 2f
        val halfHeight = height / 2f
        val list = ArrayList<PointF>(6)

        list.add(PointF(halfWidth / 2, halfHeight / 2))
        list.add(PointF(halfWidth * 1.5f, halfHeight / 2))

        list.add(PointF(halfWidth / 2, halfHeight * 1))
        list.add(PointF(halfWidth * 1.5f, halfHeight * 1))

        list.add(PointF(halfWidth / 2, halfHeight * 1.5f))
        list.add(PointF(halfWidth * 1.5f, halfHeight * 1.5f))
        return list
    }

}