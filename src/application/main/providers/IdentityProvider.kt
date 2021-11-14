package application.main.providers

import application.main.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.services.Encoder
import application.main.services.UserService

class IdentityProvider {
    private val userService = UserService()
    private val authProvider = AuthorityProvider()

    fun identityProvide(input: Input): Pair<User, ExitCode> {
        if (!Validator.validateLogin(input.login!!))
            return Pair(User(), ExitCode.LOGIN_FORMAT_INCORRECT)

        val gotDto = userService.findUserByLogin(input.login!!) ?: return Pair(User(), ExitCode.LOGIN_INCORRECT)

        val encodedPass = Encoder.encode(Encoder.encode(input.password!!) + gotDto.salt)

        return if (encodedPass == gotDto.password) {
            if (input.role != null && input.resource != null)
                authProvider.resourceProvide(input)
            else
                Pair(User(input.login), ExitCode.SUCCESS)
        } else
            Pair(User(), ExitCode.PASSWORD_INCORRECT)
    }
}