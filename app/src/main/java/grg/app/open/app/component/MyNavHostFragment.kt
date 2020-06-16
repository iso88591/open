package grg.app.open.app.component

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import grg.app.open.R

class MyNavHostFragment : NavHostFragment() {


    val myFragmentNav by lazy {
        MyFragmentNavigator(
            requireContext(), childFragmentManager,
            getContainerId()
        )
    }

    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        return myFragmentNav
    }

    private fun getContainerId(): Int {
        val id = id
        return if (id != 0 && id != View.NO_ID) {
            id
        } else R.id.nav_host_fragment_container
        // Fallback to using our own ID if this Fragment wasn't added via
        // add(containerViewId, Fragment)
    }

    @Navigator.Name("fragment")
    inner class MyFragmentNavigator(
        val mContext: Context,
        val mFragmentManager: FragmentManager,
        val containerId: Int
    ) : FragmentNavigator(mContext, mFragmentManager, containerId) {

        val TAG = "MyFragmentNavigator"

        override fun navigate(
            destination: Destination,
            args: Bundle?,
            navOptions: NavOptions?,
            navigatorExtras: Navigator.Extras?
        ): NavDestination? {

            mFragmentManager
                .beginTransaction()
                .show(instantiateFragment(mContext, mFragmentManager, destination.className, args))
                .commit()
            return destination
        }

        override fun instantiateFragment(
            context: Context,
            fragmentManager: FragmentManager,
            className: String,
            args: Bundle?
        ): Fragment {

            var fragment: Fragment? = null
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentManager.fragments.forEach {
                if (it.javaClass.name.equals(className)) {
                    fragment = it
                }
                fragmentTransaction.hide(it)
            }
            if (fragment != null) {
                fragmentTransaction.commit()
                fragment?.arguments = args
                return fragment!!
            }

            fragment = fragmentManager.fragmentFactory.instantiate(
                context.classLoader, className
            )
            fragment?.let {
                it?.arguments = args
                fragmentTransaction
                    .add(containerId, it)
                    .commit()
                return it
            }
            return super.instantiateFragment(context, fragmentManager, className, args)

        }

    }


}