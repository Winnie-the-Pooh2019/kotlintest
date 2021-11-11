package application.main.providers

import application.main.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode

interface IIdentityProvider {
    fun identityProvide(input: Input): Pair<User, ExitCode>
}