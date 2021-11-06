package application.main.services

import application.main.userdata.Role
import application.main.userdata.storage.UserAuthData

class AuthService : IAuthService {
    private val userAuthData = UserAuthData()

    override fun findResByLoginAndRole(login: String, role: Role): List<String> {
        return userAuthData.usersToRolesToRes.filter {
            it[0] == login && it[1] == role.name
        }.map { it[2] }
    }
}