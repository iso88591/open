package grg.app.open.app.extension

import androidx.lifecycle.Observer
import grg.app.open.app.component.ObserverObservable

fun <D> Observer<D?>.toObserverObservable(): ObserverObservable<D> {
    return ObserverObservable<D> {
        this.onChanged(it)
    }
}