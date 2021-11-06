package application.main.providers

import application.main.userdata.Role

interface IAuthorityProvider {
    fun resourceProvide(login: String, role: Role, resource: String): Boolean
}