package com.defey.testtrainer.utils

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val code: Int? = null) : NetworkResult<Nothing>()
}

suspend fun <T : Any, N : Any> NetworkResult<T>.mapSuccess(
    executable: suspend (T) -> N,
): NetworkResult<N> = when (this) {
    is NetworkResult.Error -> NetworkResult.Error(message, code)
    is NetworkResult.Success -> NetworkResult.Success(executable(data))
}

suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit,
): NetworkResult<T> = apply {
    if (this is NetworkResult.Success<T>) {
        executable(data)
    }
}

suspend fun <T : Any> NetworkResult<T>.onError(
    executable: suspend (message: String, code: Int?) -> Unit,
): NetworkResult<T> = apply {
    if (this is NetworkResult.Error) {
        executable(message, code)
    }
}

suspend fun <T : Any> NetworkResult<T>.finally(
    executable: suspend (NetworkResult<T>) -> Unit,
): NetworkResult<T> = apply {
    executable(this)
}