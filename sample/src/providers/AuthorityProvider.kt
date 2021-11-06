package providers

import services.AuthService
import userdata.Role
import java.util.regex.Pattern

class AuthorityProvider : IAuthorityProvider {
    private val authService = AuthService()
    
    override fun resourceProvide(login: String, role: Role, resource: String): Boolean {
        val resources = authService.findResByLoginAndRole(login, role)
        
        return resources.isEmpty() || !isChild(resource, resources)
    }

    companion object Child {
        fun isChild(resource: String, resources: List<String>): Boolean {
            return resources.any {
                val pattern = Pattern.compile("^$it(\\.[a-zA-Z]{1,10})*")

                return@any pattern.matcher(resource).find()
            }
        }
    }
}