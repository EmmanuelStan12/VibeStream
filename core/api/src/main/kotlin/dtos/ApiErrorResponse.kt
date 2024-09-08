package dtos

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ApiErrorResponse(
    val status: Int,
    val message: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val errors: List<String> = emptyList()
) {
    constructor(status: Int, message: String) : this(status, message, LocalDateTime.now())

    constructor(status: Int, message: String, errors: List<String>): this(status, message, LocalDateTime.now(), errors)
}