package filters

import context.AuthContext
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver
import service.UserService
import security.JwtAuthHelper
import security.JwtAuthenticationToken

class JwtAuthFilter(
    private val helper: JwtAuthHelper,
    private val exceptionResolver: HandlerExceptionResolver,
    private val userService: UserService,
    private val authContext: AuthContext
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)
        try {
            if (authorization.isNullOrBlank().not()) {
                val token = authorization.substringAfter("Bearer ")
                val username = helper.getUsernameFromToken(token)
                val user = userService.findByUsername(username)!!
                authContext.setContextProps(user, token)
            }
        } catch (e: Exception) {
            authContext.clearContext()
            exceptionResolver.resolveException(request, response, null, e)
            return;
        }

        filterChain.doFilter(request, response)
    }
}