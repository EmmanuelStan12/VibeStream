package controller

import context.AuthContext
import dtos.ApiResponse
import dtos.AuthResponse
import dtos.CreateUserRequest
import dtos.LoginRequest
import mappers.toUser
import mappers.toUserDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import security.JwtAuthenticationTokenProvider
import service.UserService

@RequestMapping("/api/auth")
@RestController
class AuthController(
    private val userService: UserService,
    private val authenticationProvider: JwtAuthenticationTokenProvider,
    private val authContext: AuthContext
) {

    @PostMapping("login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<ApiResponse<AuthResponse>> {
        val user = authenticationProvider.authenticate(loginRequest.username, loginRequest.password)
        val response = AuthResponse(authContext.getAuthToken(), user.toUserDTO())
        return ResponseEntity.ok().body(ApiResponse(response))
    }

    @PostMapping("register")
    fun register(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<ApiResponse<AuthResponse>> {
        val user = userService.register(createUserRequest.toUser())
        val token = authenticationProvider.getAuthToken(user)
        val response = AuthResponse(token, user.toUserDTO())
        return ResponseEntity.ok().body(ApiResponse(response))
    }
}