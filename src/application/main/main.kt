package application.main

import application.main.domain.Input
import application.main.services.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val input = Input.parse(args)

    val accountProvider = AccountProvider()
    val authProvider = AuthorityProvider(accountProvider, AuthService())
    val identityProvider = IdentityProvider(authProvider, UserService())

    val userToExit = identityProvider.provide(input)

    exitProcess(userToExit.code)
}