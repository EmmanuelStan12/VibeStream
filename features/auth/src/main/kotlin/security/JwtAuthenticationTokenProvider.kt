package security

import context.AuthContext
import entity.User
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import service.UserService

@Component
class JwtAuthenticationTokenProvider(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val helper: JwtAuthHelper,
    private val authContext: AuthContext
) {

    fun authenticate(username: String, password: String): User {
        val user = userService.findByUsername(username)
        val matches = passwordEncoder.matches(password, user?.password)
        if (!matches || user == null) {
            throw BadCredentialsException("Invalid username or password")
        }
        return user
    }

    fun getAuthToken(user: User): String {
        val token = helper.generateToken(user.username)
        authContext.setContextProps(user, token)
        return token
    }
}