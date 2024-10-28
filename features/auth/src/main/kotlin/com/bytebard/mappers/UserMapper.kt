package com.bytebard.mappers

import com.bytebard.dtos.CreateUserRequest
import com.bytebard.dtos.UserDTO
import com.bytebard.entity.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toUserDTO(user: User): UserDTO = UserDTO(
        user.id,
        user.name,
        user.email,
        user.username,
    )

    fun toUser(createUserRequest: CreateUserRequest): User =
        User(
            null,
            createUserRequest.name,
            createUserRequest.email,
            createUserRequest.password,
            createUserRequest.username
        )
}

