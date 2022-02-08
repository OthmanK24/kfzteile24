package de.kfzteile24.app.data.vehicles

import de.kfzteile24.app.data.vehicles.remote.api.VehiclesApi
import de.kfzteile24.app.domain.common.BaseResult
import de.kfzteile24.app.domain.common.WrappedListResponse
import de.kfzteile24.app.domain.common.WrappedResponse
import de.kfzteile24.app.domain.common.util.toEntity
import de.kfzteile24.app.domain.vehicles.VehicleRepository
import de.kfzteile24.app.domain.vehicles.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VehicleRepositoryImpl(private val vehicleApi: VehiclesApi) : VehicleRepository {
    override suspend fun getVehicles(locationName: String): Flow<BaseResult<WrappedListResponse<VehicleEntity>, WrappedResponse<String>>> {
        return flow {
            val response = vehicleApi.getVehicles(locationName = locationName)
            if (response.isSuccessful) {
                val vehicleList = mutableListOf<VehicleEntity>()
                response.body()?.let { vehicles ->
                    vehicleList.addAll(
                        vehicles.data.map { dto ->
                            dto.toEntity()
                        }
                    )
                }
                emit(
                    BaseResult.Success(
                        WrappedListResponse(
                            code = response.code(),
                            message = null,
                            status = true,
                            errors = null,
                            data = vehicleList
                        )
                    )
                )
            } else {
                emit(
                    BaseResult.Error(
                        WrappedResponse(
                            code = response.code(),
                            message = response.message(),
                            status = false,
                            errors = listOf(response.errorBody().toString()),
                            data = response.toString()
                        )
                    )
                )
            }
        }
    }
}