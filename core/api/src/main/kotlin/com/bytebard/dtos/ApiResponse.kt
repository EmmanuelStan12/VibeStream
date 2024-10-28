package com.bytebard.dtos

data class ApiResponse<T>(
    val data: T,
)