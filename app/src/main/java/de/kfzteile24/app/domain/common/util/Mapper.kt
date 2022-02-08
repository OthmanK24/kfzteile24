package de.kfzteile24.app.domain.common.util

import de.kfzteile24.app.data.vehicles.remote.dto.PositionDto
import de.kfzteile24.app.data.vehicles.remote.dto.VehicleDto
import de.kfzteile24.app.domain.vehicles.entity.PositionEntity
import de.kfzteile24.app.domain.vehicles.entity.VehicleEntity

fun PositionDto.toEntity(): PositionEntity {
    return PositionEntity(
        latitude = latitude,
        longitude = longitude
    )
}

fun PositionEntity.toDto(): PositionDto {
    return PositionDto(
        latitude = latitude,
        longitude = longitude
    )
}

fun VehicleDto.toEntity(): VehicleEntity {
    return VehicleEntity(
        fuel = fuel,
        id = id,
        locationId = locationId,
        model = model,
        numberPlate = numberPlate,
        vin = vin,
        position = this.position?.toEntity()
    )
}

fun VehicleEntity.toDto(): VehicleDto {
    return VehicleDto(
        fuel = fuel,
        id = id,
        locationId = locationId,
        model = model,
        numberPlate = numberPlate,
        vin = vin,
        position = this.position?.toDto()
    )
}