package grg.app.open.app.component

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import grg.app.open.ui.activity.MainActivity

inline fun <reified T : AppCompatActivity> AppCompatActivity.open(
    noinline intentCustom: (Intent.() -> Unit)? = null
) {
    val intent = Intent(this, T::class.java)
    intentCustom?.invoke(intent)
    this.startActivity(intent)
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.openForResult(
    requestCode: Int,
    noinline intentCustom: (Intent.() -> Unit)? = null
) {
    val intent = Intent(this, T::class.java)
    intentCustom?.invoke(intent)
    this.startActivityForResult(intent, requestCode)
}