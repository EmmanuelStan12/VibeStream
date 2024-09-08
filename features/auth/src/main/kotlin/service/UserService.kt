package service

import entity.User
import exceptions.AlreadyExistsException
import exceptions.NotFoundException

interface UserService {

    fun findByUsername(username: String): User?

    @Throws(AlreadyExistsException::class)
    fun register(user: User): User

    @Throws(NotFoundException::class)
    fun getUserById(id: Long): User
}