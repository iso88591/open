package grg.app.open.widgets

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.youth.banner.loader.ImageLoaderInterface
import grg.app.open.app.extension.loadNetImage

object SimpleBannerImageLoader : ImageLoaderInterface<ImageView> {
    override fun createImageView(context: Context?): ImageView {
        return AppCompatImageView(context)
    }

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        imageView?.loadNetImage(path as String?)
    }
}