package grg.app.open.app.extension

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView

fun<D,H:RecyclerView.ViewHolder> PagedListAdapter<D,H>.toObserver():Observer<PagedList<D>>{
    return Observer<PagedList<D>> {
        submitList(it)
    }
}