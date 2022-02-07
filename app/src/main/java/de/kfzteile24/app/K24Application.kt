package de.kfzteile24.app

import android.app.Application
import de.kfzteile24.app.di.KoinModule
import de.kfzteile24.app.di.locationModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("UNUSED")
@ExperimentalSerializationApi
class K24Application : Application() {
  
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@K24Application)
      modules(locationModule)
    }

    KoinModule.load()
  }


}