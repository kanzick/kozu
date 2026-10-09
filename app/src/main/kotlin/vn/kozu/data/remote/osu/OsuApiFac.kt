package vn.kozu.data.remote.osu

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OsuApiFactory {

    private const val BASE_URL = "https://osu.ppy.sh/api/v2/"

    fun create(accessToken: String): OsuApi {
        require(accessToken.isNotBlank()) {
            "osu! access token must not be blank"
        }

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .header("Accept", "application/json")
                    .build()

                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OsuApi::class.java)
    }
}