package com.bytebard.service.impl

import com.bytebard.entity.User
import com.bytebard.exceptions.AlreadyExistsException
import com.bytebard.exceptions.NotFoundException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import com.bytebard.repository.UserRepository
import com.bytebard.service.UserService

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun findByUsername(username: String): User? {
        val user = userRepository.findByUsernameOrEmail(username)
            ?: throw UsernameNotFoundException("User $username not found")

        return user
    }

    override fun register(user: User): User {
        if (userRepository.findByEmail(user.email) != null) {
            throw AlreadyExistsException("User ${user.email} already exists")
        }

        if (userRepository.findByUsername(user.username) != null) {
            throw AlreadyExistsException("User ${user.username} already exists")
        }
        return userRepository.save(user)
    }

    override fun getUserById(id: Long): User {
        val user = userRepository.findById(id).orElseThrow {
            NotFoundException(
                "User $id not found"
            )
        }
        return user
    }
}