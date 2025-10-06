package com.org.filmsapplication.core.utils

import androidx.compose.runtime.Composable

sealed interface ApiResponseState<out T> {
    val data: T?

    object Initial : ApiResponseState<Nothing> {
        override val data: Nothing? = null
    }

    data class Loading<T>(
        override val data: T? = null,
    ) : ApiResponseState<T>

    data class Success<T>(
        override val data: T,
    ) : ApiResponseState<T>

    data class Error<out T>(
        val throwable: Throwable,
        override val data: T? = null,
    ) : ApiResponseState<T>

    val status: ApiStatus
        get() = when (this) {
            is Initial -> ApiStatus.INITIAL
            is Loading<*> -> ApiStatus.LOADING
            is Success<*> -> ApiStatus.SUCCESS
            is Error<*> -> ApiStatus.ERROR
        }

    val isInitial: Boolean
        get() = this is Initial

    val isLoading: Boolean
        get() = this is Loading<*>

    val isSuccess: Boolean
        get() = this is Success<*>

    val isError: Boolean
        get() = this is Error<*>

    val hasData: Boolean
        get() = this.data != null

    val errorMessage: String?
        get() = when (this) {
            is Error -> throwable.message ?: "Unknown error"
            else -> null
        }

}

enum class ApiStatus {
    INITIAL, LOADING, SUCCESS, ERROR
}

@Composable
fun <T> ApiResponseState<T>.StateContent(
    onInitial: @Composable () -> Unit = {},
    onLoading: @Composable (data: T?) -> Unit = {},
    onSuccess: @Composable (data: T) -> Unit = {},
    onError: @Composable (throwable: Throwable) -> Unit = { _ -> },
) {
    when (this) {
        is ApiResponseState.Initial -> onInitial()
        is ApiResponseState.Loading -> onLoading(data)
        is ApiResponseState.Success -> onSuccess(data)
        is ApiResponseState.Error -> onError(throwable)
    }
}