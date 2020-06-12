package grg.app.open.app.extension

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

fun <T : View> RecyclerView.ViewHolder.findViewById(@IdRes id: Int): T {
    return itemView.findViewById(id)
}