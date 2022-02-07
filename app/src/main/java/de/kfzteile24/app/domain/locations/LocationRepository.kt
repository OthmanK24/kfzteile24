package de.kfzteile24.app.domain.locations

import de.kfzteile24.app.domain.locations.common.BaseResult
import de.kfzteile24.app.domain.locations.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getLocationList(): Flow<BaseResult<List<LocationEntity>,String>>
}