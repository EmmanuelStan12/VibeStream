package com.bytebard.service

import com.bytebard.entity.User
import com.bytebard.exceptions.AlreadyExistsException
import com.bytebard.exceptions.NotFoundException

interface UserService {

    fun findByUsername(username: String): User?

    @Throws(AlreadyExistsException::class)
    fun register(user: User): User

    @Throws(NotFoundException::class)
    fun getUserById(id: Long): User
}