package application.main.providers

import application.main.User

interface IIdentityProvider {
    fun identityProvide(login: String, password: String): User?
}