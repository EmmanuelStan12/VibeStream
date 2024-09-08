package dtos

data class AuthResponse(
    val accessToken: String,
    val user: UserDTO
)