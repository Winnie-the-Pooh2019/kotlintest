package providers

import userdata.Role

interface IAuthorityProvider {
    fun resourceProvide(login: String, role: Role, resource: String): Boolean
}