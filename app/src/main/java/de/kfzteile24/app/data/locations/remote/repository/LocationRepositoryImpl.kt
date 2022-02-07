package de.kfzteile24.app.data.locations.remote.repository

import de.kfzteile24.app.data.locations.remote.api.LocationApi
import de.kfzteile24.app.domain.locations.LocationRepository
import de.kfzteile24.app.domain.locations.common.BaseResult
import de.kfzteile24.app.domain.locations.entity.LocationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationRepositoryImpl(private val locationApi: LocationApi): LocationRepository {
    override suspend fun getLocationList(): Flow<BaseResult<List<LocationEntity>, String>> {
        return flow {
            val response = locationApi.getLocations()
            if(response.isSuccessful){
                val locationList = mutableListOf<LocationEntity>()
                response.body()?.let { response->
                    locationList.addAll(response.locationData.map { dto->
                        LocationEntity(dto.id,dto.name)
                    })
                }
                emit(BaseResult.Success(locationList))
            }else{
                emit(BaseResult.Error(response.message()))
            }
        }
    }
}