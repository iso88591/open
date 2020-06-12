package grg.app.open.app.extension

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import grg.app.open.R

//var View.databinding: ViewDataBinding
//    get() = getTag(R.id.dataBinding) as ViewDataBinding
//    set(value) {
//        setTag(R.id.dataBinding, value)
//    }

fun <binding : ViewDataBinding> View.databinding(): binding {
    var databinding = getTag(R.id.dataBinding) as binding?
    if (databinding == null) {
        databinding = DataBindingUtil.bind<binding>(this)
        databinding<binding>(databinding!!)
    }
    return databinding
}

fun <binding : ViewDataBinding> View.databinding(dataBinding: ViewDataBinding) {
    setTag(R.id.dataBinding, dataBinding)
}



