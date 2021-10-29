package providers

import User
import exceptions.NoSuchRoleException
import userdata.DataService
import userdata.Encoder
import userdata.Role

class Provider : IProvider {
    private val dataService = DataService()

    override fun identityProvide(login: String, password: String): Boolean {
        val gotDto = dataService.receiveUserData(login)
        val encodedPass = Encoder.encode(password + gotDto.salt)

        return encodedPass == gotDto.password
    }

    // add resource access granting
    override fun authorityProvide(login: String, role: String): User? {
        if (role !in Role.values().map { it.name })
            throw NoSuchRoleException("There is no role: $role")

        val susRole = Role.valueOf(role)
        val gotRole = dataService.receiveUserRoles(login)

        return if (gotRole == susRole) User(login, susRole) else null
    }
}