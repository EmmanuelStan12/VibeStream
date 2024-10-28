package com.bytebard.controller

import com.bytebard.context.AuthContext
import com.bytebard.dtos.ApiResponse
import com.bytebard.dtos.AuthResponse
import com.bytebard.dtos.CreateUserRequest
import com.bytebard.dtos.LoginRequest
import com.bytebard.mappers.UserMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.bytebard.security.JwtAuthenticationTokenProvider
import com.bytebard.service.UserService

@RequestMapping("/api/auth")
@RestController
class AuthController(
    private val userService: UserService,
    private val authenticationProvider: JwtAuthenticationTokenProvider,
    private val authContext: AuthContext,
    private val userMapper: UserMapper
) {

    @PostMapping("login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<ApiResponse<AuthResponse>> {
        val user = authenticationProvider.authenticate(loginRequest.username, loginRequest.password)
        val response = AuthResponse(authContext.getAuthToken(), userMapper.toUserDTO(user))
        return ResponseEntity.ok().body(ApiResponse(response))
    }

    @PostMapping("register")
    fun register(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<ApiResponse<AuthResponse>> {
        val user = userService.register(userMapper.toUser(createUserRequest))
        val token = authenticationProvider.getAuthToken(user)
        val response = AuthResponse(token, userMapper.toUserDTO(user))
        return ResponseEntity.ok().body(ApiResponse(response))
    }
}