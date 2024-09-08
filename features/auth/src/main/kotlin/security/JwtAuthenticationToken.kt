package security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken: AbstractAuthenticationToken {

    private lateinit var principal: Any
    private lateinit var credentials: Any

    constructor(authorities: MutableCollection<out GrantedAuthority>?) : super(authorities)

    constructor(principal: Any, credentials: Any, authorities: MutableCollection<out GrantedAuthority> = mutableListOf()) : super(authorities) {
        this.principal = principal
        this.credentials = credentials
        isAuthenticated = true
    }

    override fun getCredentials(): Any {
        return credentials
    }

    override fun getPrincipal(): Any {
        return principal
    }
}