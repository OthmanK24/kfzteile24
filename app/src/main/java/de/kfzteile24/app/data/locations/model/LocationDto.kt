package de.kfzteile24.app.data.locations.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
  @SerialName("id") val id: String,
  @SerialName("name") val name: String
)