package de.kfzteile24.app.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

internal class RetrofitFactory {

  @ExperimentalSerializationApi
  fun build(json: Json): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .client(getClient())
      .baseUrl("URL")
      .build()
  }

  private fun getClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    addTimeouts(builder)

    return builder.build()
  }

  private fun addTimeouts(builder: OkHttpClient.Builder, timeout: Int = 30) {
    builder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
  }
}