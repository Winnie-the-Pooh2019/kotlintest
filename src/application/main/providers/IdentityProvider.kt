package application.main.providers

import application.main.input.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.services.Encoder
import application.main.services.UserService

class IdentityProvider(private val provider: IProvider) : IProvider {
    private val userService = UserService()

    override fun provide(input: Input): User {
        if (input.identityInput == null)
            return User(status = ExitCode.OK)

        if (!Validator.validateLogin(input.identityInput.login))
            return User(status = ExitCode.LOGIN_FORMAT_INCORRECT)

        val gotDto = userService.findUserByLogin(input.identityInput.login) ?: return User(status = ExitCode.LOGIN_INCORRECT)

        val encodedPass = Encoder.encode(Encoder.encode(input.identityInput.password) + gotDto.salt)

        return if (encodedPass == gotDto.password) {
            provider.provide(input)
        } else
            User(status = ExitCode.PASSWORD_INCORRECT)
    }
}