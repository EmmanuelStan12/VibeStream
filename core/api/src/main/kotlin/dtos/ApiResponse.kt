package dtos

data class ApiResponse<T>(
    val data: T,
)