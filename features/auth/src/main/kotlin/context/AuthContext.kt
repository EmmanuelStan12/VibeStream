package context

import entity.User

interface AuthContext {
    fun getCurrentUser(): User

    fun clearContext()

    fun setContextProps(user: User, token: String)

    fun getAuthToken(): String
}