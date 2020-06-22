package grg.app.open.app.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleDivideItemDec(
    val callback: ((Rect, View, RecyclerView, RecyclerView.State) -> Unit)? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        callback?.invoke(outRect, view, parent, state)
    }
}

inline fun RecyclerView.simpleDivideItemDec(crossinline dec: (Rect.() -> Unit)) {
    this.divideItemDec { rect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State ->
        dec.invoke(rect)
    }
}

fun RecyclerView.divideItemDec(callback: ((Rect, View, RecyclerView, RecyclerView.State) -> Unit)) {
    addItemDecoration(SimpleDivideItemDec(callback))
}