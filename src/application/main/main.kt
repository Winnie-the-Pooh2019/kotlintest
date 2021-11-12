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

    when (userToExit.second) {
        ExitCode.ACCESS_DENIED -> exitProcess(ExitCode.ACCESS_DENIED.ordinal)

        ExitCode.LOGIN_FORMAT_INCORRECT -> exitProcess((ExitCode.LOGIN_FORMAT_INCORRECT.ordinal))

        ExitCode.LOGIN_INCORRECT -> exitProcess(ExitCode.LOGIN_INCORRECT.ordinal)

        ExitCode.PASSWORD_INCORRECT -> exitProcess(ExitCode.PASSWORD_INCORRECT.ordinal)

        ExitCode.ROLE_UNKNOWN -> exitProcess(ExitCode.ROLE_UNKNOWN.ordinal)

        ExitCode.SUCCESS -> exitProcess(ExitCode.SUCCESS.ordinal)

        ExitCode.SUSPICIOUS_ACTIVITY -> exitProcess(ExitCode.SUSPICIOUS_ACTIVITY.ordinal)

        ExitCode.HELP -> {
//            showFaq()
            exitProcess(ExitCode.HELP.ordinal)
        }
    }
}