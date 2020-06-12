package grg.app.open.app.extension

import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadNetImage(url: String?) {
    if (TextUtils.isEmpty(url)) return

    //simple

    Glide
        .with(this)
        .load(url)
        .into(this)

}