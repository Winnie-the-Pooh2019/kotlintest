package application.main.services

import application.main.domain.Input
import application.main.domain.ExitCode

class IdentityProvider(private val provider: IProvider, private val userService: UserService) : IProvider {
    override fun provide(input: Input): ExitCode {
        if (!input.identityInput.exists)
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