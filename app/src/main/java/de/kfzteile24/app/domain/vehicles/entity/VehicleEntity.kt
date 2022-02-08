package de.kfzteile24.app.domain.vehicles.entity


data class VehicleEntity(
    val fuel: Double,
    val id: Int,
    val locationId: Int,
    val model: String,
    val numberPlate: String,
    val position: PositionEntity?,
    val vin: String
)