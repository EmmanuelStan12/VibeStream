package repository

import entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

    fun findByUsername(username: String): User?

    @Query("SELECT u FROM User u WHERE u.username = :emailOrUsername OR u.email = :emailOrUsername")
    fun findByUsernameOrEmail(@Param("emailOrUsername") emailOrUsername: String): User?
}