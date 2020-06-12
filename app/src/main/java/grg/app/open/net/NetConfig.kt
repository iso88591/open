package grg.app.open.net

import android.util.Log
import androidx.lifecycle.LiveData
import grg.app.open.net.bean.IndexArticle
import grg.app.open.net.bean.IndexBanner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object NetConfig {

    interface WanAndroid {

        @GET("https://www.wanandroid.com/article/list/{page}/json")
        fun indexArticleList(@Path("page") page: Int): LiveData<ApiWrapper<IndexArticle>>

        @GET("https://www.wanandroid.com/banner/json")
        fun indexBanner(): LiveData<ApiWrapper<List<IndexBanner>>>

        companion object {
            const val TAG = "OpenApi"

            private val simpleRetrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
                retrofitFactory({
                    baseUrl("https://www.wanandroid.com")
                }, {
                    addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String) {
                            Log.d(TAG, "log: $message")
                        }
                    }).apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })

                })
            }

            private val wanAndroidAPi by lazy { simpleRetrofit.create(WanAndroid::class.java) }

            fun get() = wanAndroidAPi
        }
    }

    private fun retrofitFactory(
        config: Retrofit.Builder.() -> Unit,
        clientConfig: OkHttpClient.Builder.() -> Unit
    ): Retrofit {
        return Retrofit.Builder()
            .apply {
                client(okHttpClientFactory(clientConfig))

                addConverterFactory(GsonConverterFactory.create())

                addCallAdapterFactory(LiveDataCallAdapterFactory(ApiWrapper::class.java) { call: Call<*>, throwable: Throwable ->

                    ApiWrapper.error("未知错误")

                })

                config.invoke(this)
            }
            .build()
    }

    private fun okHttpClientFactory(config: OkHttpClient.Builder.() -> Unit): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                config.invoke(this)
            }
            .build()
    }

}