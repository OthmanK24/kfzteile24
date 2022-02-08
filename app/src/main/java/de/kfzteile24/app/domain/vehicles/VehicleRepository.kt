package de.kfzteile24.app.domain.vehicles

import de.kfzteile24.app.domain.common.BaseResult
import de.kfzteile24.app.domain.common.WrappedListResponse
import de.kfzteile24.app.domain.common.WrappedResponse
import de.kfzteile24.app.domain.vehicles.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun getVehicles(locationName: String): Flow<BaseResult<WrappedListResponse<VehicleEntity>, WrappedResponse<String>>>
}