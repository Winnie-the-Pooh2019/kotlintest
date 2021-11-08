package application.main.providers

import application.main.User
import application.main.services.Encoder
import application.main.services.UserService

class IdentityProvider : IIdentityProvider {
    private val userService = UserService()

    override fun identityProvide(login: String, password: String): User? {
        if (!Validator.validateLogin(login))
            throw Exception("login don't match the pattern")

        val gotDto = userService.findUserByLogin(login) ?: throw Exception("no such login")
        val encodedPass = Encoder.encode(Encoder.encode(password) + gotDto.salt)

        return if (encodedPass == gotDto.password) User(login) else null
    }
}