package application.main.providers

import application.main.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode

interface IAccountProvider {
    fun accountProvide(input: Input): Pair<User, ExitCode>
}
