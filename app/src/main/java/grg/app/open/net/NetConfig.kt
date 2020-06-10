package grg.app.open.net

import androidx.lifecycle.LiveData
import grg.app.open.net.bean.IndexArticle
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object NetConfig {

    interface WanAndroid {

        @GET("https://www.wanandroid.com/article/list/{page}/json")
        fun indexArticleList(@Path("page") page: Int): LiveData<ApiWrapper<IndexArticle>>

        companion object {
            private val simpleRetrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
                retrofitFactory({
                    baseUrl("https://www.wanandroid.com")
                }, {

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