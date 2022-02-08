package de.kfzteile24.app.di

import de.kfzteile24.app.data.vehicles.VehicleRepositoryImpl
import de.kfzteile24.app.data.vehicles.remote.api.VehiclesApi
import de.kfzteile24.app.domain.vehicles.VehicleRepository
import de.kfzteile24.app.domain.vehicles.usecase.VehicleUseCase
import de.kfzteile24.app.presentation.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val vehicleModule = module {

    factory { VehicleUseCase(get()) }

    single<VehiclesApi> { get<Retrofit>().create(VehiclesApi::class.java) }

    single<VehicleRepository> { VehicleRepositoryImpl(get()) }

    viewModel { DetailViewModel(get()) }
}