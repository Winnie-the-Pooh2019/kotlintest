package application.main.providers

import application.main.User
import application.main.services.AuthService
import application.main.userdata.Role
import java.util.regex.Pattern

class AuthorityProvider : IAuthorityProvider {
    private val authService = AuthService()

    override fun resourceProvide(login: String, role: String, resource: String): User? {
        if (!Validator.validateRole(role))
            throw Exception("non-existing role")

        if (!Validator.validateResource(resource))
            throw Exception("incorrect resource format")

        val resources = authService.findResByLoginAndRole(login, Role.valueOf(role))

        return  if (resources.isEmpty() || !isChild(resource, resources)) User(login, Role.valueOf(role), resource) else null
    }

    override fun resourceProvide(user: User): User? {
        return resourceProvide(user.login!!, user.role!!.name, user.resource!!)
    }

    private fun isChild(resource: String, resources: List<String>): Boolean {
        return resources.any {
            val pattern = Pattern.compile("^$it(\\.[a-zA-Z]{1,10})*")

            return@any pattern.matcher(resource).find()
        }
    }
}