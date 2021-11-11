package application.main.providers

import application.main.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.userdata.Role

interface IAuthorityProvider {
    fun resourceProvide(input: Input): Pair<User, ExitCode>
}