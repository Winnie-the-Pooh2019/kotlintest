package application.main

import application.main.domain.Input
import application.main.services.AccountProvider
import application.main.services.AuthorityProvider
import application.main.services.IdentityProvider
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val input = Input(args)

    val accountProvider = AccountProvider()
    val authProvider = AuthorityProvider(accountProvider)
    val identityProvider = IdentityProvider(authProvider)

    val userToExit = identityProvider.provide(input)

    exitProcess(userToExit.code)
}