package application.main.providers

import application.main.User
import application.main.services.AuthService
import application.main.userdata.Role
import java.util.regex.Pattern

class AuthorityProvider : IAuthorityProvider {
    private val authService = AuthService()

    override fun resourceProvide(login: String, role: Role, resource: String): User? {
        if (!Validator.validateResource(resource))
            throw Exception("incorrect resource format")

        val resources = authService.findResByLoginAndRole(login, role)

        return  if (resources.isEmpty() || !isChild(resource, resources)) User(login, role, resource) else null
    }

    override fun resourceProvide(user: User): User? {
        return resourceProvide(user.login!!, user.role!!, user.resource!!)
    }

    private fun isChild(resource: String, resources: List<String>): Boolean {
        return resources.any {
            val pattern = Pattern.compile("^$it(\\.[a-zA-Z]{1,10})*")

            return@any pattern.matcher(resource).find()
        }
    }
}