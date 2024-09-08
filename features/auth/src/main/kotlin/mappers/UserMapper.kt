package mappers

import dtos.CreateUserRequest
import dtos.UserDTO
import entity.User

fun User.toUserDTO(): UserDTO = UserDTO(
    id,
    name,
    email,
    username,
)

fun CreateUserRequest.toUser(): User = User(
    null,
    name,
    email,
    password,
    username
)

