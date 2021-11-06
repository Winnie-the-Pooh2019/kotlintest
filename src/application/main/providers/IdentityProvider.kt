package application.main.providers

import application.main.services.Encoder
import application.main.services.UserService

class IdentityProvider : IIdentityProvider {
    private val userService = UserService()

    override fun identityProvide(login: String, password: String): Boolean {
        val gotDto = userService.findUserByLogin(login) ?: throw Exception("no such login")
        val encodedPass = Encoder.encode(Encoder.encode(password) + gotDto.salt)

        return encodedPass == gotDto.password
    }
}