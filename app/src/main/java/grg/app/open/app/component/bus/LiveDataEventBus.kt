package grg.app.open.app.component.bus

import android.text.Editable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import grg.app.open.app.component.EventId
import java.lang.reflect.ParameterizedType

object LiveDataEventBus {

    val busCollection: HashMap<Int, MutableLiveData<*>> = HashMap()
    private var afterText: (Editable?.() -> Unit)? = null
    /**
     * 订阅目标 id的事件
     */
    fun <D> subscribe(eventId: EventId): MutableLiveData<D?> {
        val live: MutableLiveData<D?> =
            (busCollection[eventId] ?: MutableLiveData<D?>()) as MutableLiveData<D?>
        busCollection.put(eventId.actionId, live)
        return live
    }

    /**
     * 发送指定 id 的事件对应
     */
    fun post(eventId: EventId, data: Any?) {
        val live = busCollection[eventId.actionId]

        if (data == null) {
            live?.postValue(null)
        } else {
            if (data.javaClass == eventId.dataTypeClass) {
                (live as MutableLiveData<Any>).postValue(data)
            }else{
                error("""
注册事件 
指定的actionId为${eventId.actionId} 对应数据类型为${eventId.dataTypeClass.name} 
但所给值类型为${data.javaClass.name} 与注册不相符 请检查 actionId 对应数据类型 
或者检查注册类型是否是想要的类型
对应actionId 注册和回调只能用相同的类型
                """.trimIndent())
            }
        }

    }

}

fun <D> LifecycleOwner.subscribe(eventId: EventId, observer: Observer<D?>) {
    LiveDataEventBus.subscribe<D?>(eventId).observe(this, observer)
}

inline fun <reified D> post(eventId: EventId, d: D?) {
    LiveDataEventBus.post(eventId, d)
}

inline fun post(eventId: EventId) {
    post<Any?>(eventId, null)
}
