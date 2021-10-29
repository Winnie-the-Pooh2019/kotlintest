package providers

import User
import userdata.Role

interface IProvider {
    @Throws(NoSuchElementException::class)
    fun identityProvide(login: String, password: String): Boolean

    fun authorityProvide(login: String, role: String): User?
}