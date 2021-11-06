package application.main.providers

interface IIdentityProvider {
    fun identityProvide(login: String, password: String): Boolean
}