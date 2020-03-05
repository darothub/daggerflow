package com.example.daggertwo

// A generic class that contains data and status about loading this data.
sealed class ResourceResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResourceResponse<T>(data)
    class Loading<T>(data: T? = null, message: String) : ResourceResponse<T>(data, message)
    class Logout<T>(message: String, data: T? = null) : ResourceResponse<T>(data, message)
    class Error<T>(message: String, data: T? = null) : ResourceResponse<T>(data, message)
}
