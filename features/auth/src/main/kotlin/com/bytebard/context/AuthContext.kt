package com.bytebard.context

import com.bytebard.entity.User

interface AuthContext {
    fun getCurrentUser(): User

    fun clearContext()

    fun setContextProps(user: User, token: String)

    fun getAuthToken(): String
}