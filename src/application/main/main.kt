package application.main

import application.main.input.Input
import application.main.providers.AccountProvider
import application.main.providers.AuthorityProvider
import application.main.providers.IdentityProvider
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val input = Input(args)

    val accountProvider = AccountProvider()
    val authProvider = AuthorityProvider(accountProvider)
    val identityProvider = IdentityProvider(authProvider)

    val userToExit = identityProvider.provide(input)

    exitProcess(userToExit.code)
}