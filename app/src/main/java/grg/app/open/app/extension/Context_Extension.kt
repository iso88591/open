package grg.app.open.app.extension

import android.content.Context
import android.widget.Toast
import grg.app.open.app.MyApplication

fun toast(string: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, string, duration).show()
}