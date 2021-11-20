package application.main.services

import application.main.userdata.Role
import application.main.userdata.storage.UserAuthData

class AuthService {
    fun findResByLoginAndRole(login: String, role: Role): List<String> {
        return UserAuthData.usersToRolesToRes.filter {
            it.login == login && it.role == role
        }.map { it.resource }
    }
}