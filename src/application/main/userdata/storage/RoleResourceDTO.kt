package application.main.userdata.storage

import application.main.userdata.Role

data class RoleResourceDTO(val login: String, val role: Role, val resource: String)