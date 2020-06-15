package grg.app.open.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentActivity

open class BaseFragment(
    val contentLayoutId: Int
) : AppCompatDialogFragment() {

    var mActivity: AppCompatActivity? = null
    lateinit var mRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = getActivity() as AppCompatActivity?

    }

    fun <T : View?> findViewById(@IdRes id: Int): T? {
        return mRootView.findViewById<T>(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(contentLayoutId, container, false)
        onCreateView(savedInstanceState)
        return mRootView
    }

    open fun onCreateView(savedInstanceState: Bundle?){

    }



}