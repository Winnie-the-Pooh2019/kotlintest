package providers

interface IIdentityProvider {
    @Throws(NoSuchElementException::class)
    fun identityProvide(login: String, password: String): Boolean

//    fun authorityProvide(login: String, role: String, sourcePath: List<String>): User

//    @Throws(IncorrectRoleException::class)
//    fun resourceProvide(user: User, sourcePath: List<String>): User
}