package security

import dtos.ApiErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class DefaultAuthenticationExceptionHandler {

    @ExceptionHandler(value = [AuthenticationException::class, AuthenticationCredentialsNotFoundException::class])
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleAuthenticationException(e: Exception): ResponseEntity<ApiErrorResponse> {
        var message = e.message
        if (e is AuthenticationCredentialsNotFoundException) {
            message = "Invalid JWT token or Authentication credentials"
        }
        val apiResponse = ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(), message!!)
        return ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED)
    }
}