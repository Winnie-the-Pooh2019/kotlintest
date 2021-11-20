package application.main.services

import application.main.domain.Role
import application.main.dto.UserAuthData

class AuthService {
    fun findResByLoginAndRole(login: String, role: Role): List<String> {
        return UserAuthData.usersToRolesToRes.filter {
            it.login == login && it.role == role
        }.map { it.resource }
    }
}