package de.kfzteile24.app.domain.locations.usecase

import de.kfzteile24.app.domain.locations.LocationRepository
import de.kfzteile24.app.domain.common.BaseResult
import de.kfzteile24.app.domain.locations.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

class LocationUseCase constructor(private val repository: LocationRepository){
    suspend fun execute() : Flow<BaseResult<List<LocationEntity>,String>> {
        return repository.getLocationList()
    }
}