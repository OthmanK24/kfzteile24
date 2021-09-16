package de.kfzteile24.app.di

import de.kfzteile24.app.data.RetrofitFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object KoinModule {

  @ExperimentalSerializationApi
  fun load() {
    loadKoinModules(module {
      //JSON
      single { Json { ignoreUnknownKeys = true } }

      //  retrofit
      single { RetrofitFactory().build(json = get()) }

    })
  }
}