package providers

import User
import exceptions.IncorrectRoleException
import exceptions.NoSuchLoginException
import exceptions.NoSuchRoleException
import userdata.DataService
import userdata.Encoder
import userdata.Role

class Provider : IProvider {
    private val dataService = DataService()

    @Throws(NoSuchLoginException::class)
    override fun identityProvide(login: String, password: String): Boolean {
        val gotDto = dataService.receiveUserData(login)
        val encodedPass = Encoder.encode(password + gotDto.salt)

        return encodedPass == gotDto.password
    }

    // add resource access granting
    @Throws(NoSuchRoleException::class, IncorrectRoleException::class)
    override fun authorityProvide(login: String, role: String): User {
        if (role !in Role.values().map { it.name })
            throw NoSuchRoleException("There is no role: $role")

        val susRole = Role.valueOf(role)
        val gotRole = dataService.receiveUserRoles(login)

        if (gotRole != susRole)
            throw IncorrectRoleException("Incorrect role")
        else
            return User(login, susRole)
    }
}