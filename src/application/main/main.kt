package application.main

import application.main.providers.AccountProvider
import application.main.providers.AuthorityProvider
import application.main.providers.IdentityProvider
import application.main.providers.exitcodes.ExitCode
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val input = Input(args)

    val accountProvider = AccountProvider()
    val authProvider = AuthorityProvider(accountProvider)
    val identityProvider = IdentityProvider(authProvider)

    val userToExit = if (input.login != null && input.password != null)
        identityProvider.provide(input)
    else
        User(status = ExitCode.HELP)

    exitProcess(userToExit.status.code)
}