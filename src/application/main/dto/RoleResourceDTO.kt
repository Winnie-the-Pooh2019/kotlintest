package application.main.dto

import application.main.domain.Role

data class RoleResourceDTO(val login: String, val role: Role, val resource: String)