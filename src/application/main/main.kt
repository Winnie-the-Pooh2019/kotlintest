package application.main

import application.main.providers.IdentityProvider
import application.main.providers.exitcodes.ExitCode
import kotlinx.cli.ArgParser
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val input = Input(args)

    val provider = IdentityProvider()

    val userToExit = if (input.login != null && input.password != null)
        provider.identityProvide(input)
    else
        Pair(User(), ExitCode.HELP)

    exitProcess(userToExit.second.code)
}