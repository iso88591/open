package grg.app.open.net//
import androidx.lifecycle.LiveData
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.KClass

class LiveDataCallAdapterFactory<T>(
    val targetWrapperClass: Class<T>,
    val failedDispatcher: (call: Call<*>, Throwable) -> T
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) return null
        //获取第一个泛型类型
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(observableType)
        if (rawType != targetWrapperClass) {
            throw IllegalArgumentException("type must be ApiResponse")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        return LiveDataCallAdapter<T>(observableType, failedDispatcher)
    }

    class LiveDataCallAdapter<T>(
        val responseType: Type,
        val failedDispatcher: (call: Call<T>, Throwable) -> T
    ) : CallAdapter<T, LiveData<T>> {

        override fun adapt(call: Call<T>): LiveData<T> {
            return object : LiveData<T>() {
                private val started = AtomicBoolean(false)
                override fun onActive() {
                    super.onActive()
                    if (started.compareAndSet(false, true)) {//确保执行一次
                        call.enqueue(object : Callback<T> {
                            override fun onFailure(call: Call<T>, t: Throwable) {
                                val value = failedDispatcher.invoke(call, t)
                                postValue(value)
                            }

                            override fun onResponse(call: Call<T>, response: Response<T>) {
                                postValue(response.body())
                            }

                        })
                    }
                }
            }
        }


        override fun responseType(): Type {
            return responseType
        }

    }
}