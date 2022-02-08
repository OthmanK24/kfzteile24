package de.kfzteile24.app.domain.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WrappedListResponse<T> (
    var code: Int,
    @SerialName("message") var message : String?,
    @SerialName("status") var status : Boolean?,
    @SerialName("errors") var errors : List<String>? = null,
    @SerialName("data") var data : List<T>? = null
)

@Serializable
data class WrappedResponse<T> (
    var code: Int,
    @SerialName("message") var message : String?,
    @SerialName("status") var status : Boolean?,
    @SerialName("errors") var errors : List<String>? = null,
    @SerialName("data") var data : T? = null
)