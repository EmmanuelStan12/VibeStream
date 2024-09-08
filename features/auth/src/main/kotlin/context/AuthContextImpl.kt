package context

import entity.User
import org.springframework.security.core.context.SecurityContextHolder
import security.JwtAuthenticationToken

class AuthContextImpl: AuthContext {
    override fun getCurrentUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication as JwtAuthenticationToken
        return authentication.principal as User
    }

    override fun clearContext() {
        SecurityContextHolder.clearContext()
    }

    override fun setContextProps(user: User, token: String) {
        val authentication = JwtAuthenticationToken(user, token, mutableListOf())
        SecurityContextHolder.getContext().authentication = authentication
    }

    override fun getAuthToken(): String {
        val authentication = SecurityContextHolder.getContext().authentication as JwtAuthenticationToken
        return authentication.credentials as String
    }
}