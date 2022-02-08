package de.kfzteile24.app.domain.vehicles.usecase

import de.kfzteile24.app.domain.common.BaseResult
import de.kfzteile24.app.domain.common.WrappedListResponse
import de.kfzteile24.app.domain.common.WrappedResponse
import de.kfzteile24.app.domain.vehicles.VehicleRepository
import de.kfzteile24.app.domain.vehicles.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

class VehicleUseCase constructor(private val repository: VehicleRepository) {
    suspend fun execute(locationName: String): Flow<BaseResult<WrappedListResponse<VehicleEntity>, WrappedResponse<String>>> {
        return repository.getVehicles(locationName)
    }
}