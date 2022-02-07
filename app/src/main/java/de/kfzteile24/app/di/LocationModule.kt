package de.kfzteile24.app.di

import de.kfzteile24.app.data.locations.remote.api.LocationApi
import de.kfzteile24.app.data.locations.remote.repository.LocationRepositoryImpl
import de.kfzteile24.app.domain.locations.LocationRepository
import de.kfzteile24.app.domain.locations.usecase.LocationUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val locationModule = module {

    factory { LocationUseCase(get()) }

    single<LocationApi> { get<Retrofit>().create(LocationApi::class.java) }

    single<LocationRepository> { LocationRepositoryImpl(get()) }

}