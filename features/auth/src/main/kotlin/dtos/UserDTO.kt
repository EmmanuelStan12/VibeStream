package dtos

data class UserDTO(
    var id: Long?,
    val name: String,
    val email: String,
    val username: String,
) {

    override fun toString(): String {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val userDTO = other as UserDTO
        return id == userDTO.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}