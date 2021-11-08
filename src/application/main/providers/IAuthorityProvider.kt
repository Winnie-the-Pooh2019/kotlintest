package application.main.providers

import application.main.User
import application.main.userdata.Role

interface IAuthorityProvider {
    fun resourceProvide(login: String, role: Role, resource: String): User?

    fun resourceProvide(user: User): User?
}