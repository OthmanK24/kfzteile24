package de.kfzteile24.app.di

import de.kfzteile24.app.data.locations.remote.api.LocationApi
import de.kfzteile24.app.data.locations.LocationRepositoryImpl
import de.kfzteile24.app.domain.locations.LocationRepository
import de.kfzteile24.app.domain.locations.usecase.LocationUseCase
import de.kfzteile24.app.presentation.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val locationModule = module {

    factory { LocationUseCase(get()) }

    single<LocationApi> { get<Retrofit>().create(LocationApi::class.java) }

    single<LocationRepository> { LocationRepositoryImpl(get()) }

    viewModel { LocationViewModel(get()) }
}