package application.main.providers

import application.main.input.Input
import application.main.providers.exitcodes.ExitCode
import application.main.services.Encoder
import application.main.services.UserService

class IdentityProvider(private val provider: IProvider) : IProvider {
    private val userService = UserService()

    override fun provide(input: Input): ExitCode {
        if (input.identityInput == null)
            return ExitCode.OK

        if (!Validator.validateLogin(input.identityInput.login))
            return ExitCode.LOGIN_FORMAT_INCORRECT

        val gotDto = userService.findUserByLogin(input.identityInput.login) ?: return ExitCode.LOGIN_INCORRECT

        val encodedPass = Encoder.hash(input.identityInput.password, gotDto.salt)

        return if (encodedPass == gotDto.password) {
            provider.provide(input)
        } else
            ExitCode.PASSWORD_INCORRECT
    }
}