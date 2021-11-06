package application.main.providers

import application.main.services.AuthService
import application.main.userdata.Role
import java.util.regex.Pattern

class AuthorityProvider : IAuthorityProvider {
    private val authService = AuthService()

    override fun resourceProvide(login: String, role: Role, resource: String): Boolean {
        val resources = authService.findResByLoginAndRole(login, role)

        return resources.isEmpty() || !isChild(resource, resources)
    }

    private fun isChild(resource: String, resources: List<String>): Boolean {
        return resources.any {
            val pattern = Pattern.compile("^$it(\\.[a-zA-Z]{1,10})*")

            return@any pattern.matcher(resource).find()
        }
    }
}