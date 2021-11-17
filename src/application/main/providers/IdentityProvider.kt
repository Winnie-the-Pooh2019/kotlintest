package application.main.providers

import application.main.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.services.Encoder
import application.main.services.UserService

class IdentityProvider : IProvider {
    private val userService = UserService()
    private val authProvider = AuthorityProvider()

    override fun provide(input: Input): User {
        if (!Validator.validateLogin(input.login))
            return User(status = ExitCode.LOGIN_FORMAT_INCORRECT)

        val gotDto = userService.findUserByLogin(input.login) ?: return User(status = ExitCode.LOGIN_INCORRECT)

        val encodedPass = Encoder.encode(Encoder.encode(input.password) + gotDto.salt)

        return if (encodedPass == gotDto.password) {
            if (input.role != "null" && input.resource != "null")
                authProvider.provide(input)
            else
                User(input.login, status = ExitCode.SUCCESS)
        } else
            User(status = ExitCode.PASSWORD_INCORRECT)
    }
}