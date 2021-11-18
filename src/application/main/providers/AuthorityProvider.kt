package application.main.providers

import application.main.input.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.services.AuthService
import application.main.userdata.Role
import java.util.regex.Pattern

class AuthorityProvider(private val provider: IProvider) : IProvider {
    private val authService = AuthService()

    private fun isChild(resource: String, resources: List<String>): Boolean {
        return resources.any {
            println("$resource | $it")
            val pattern = Pattern.compile("^$it(\\.[a-zA-Z]{1,10})*$")

            return@any pattern.matcher(resource).find()
        }
    }

    override fun provide(input: Input): User {
        if (input.authInput == null)
            return User(input.identityInput!!.login, status = ExitCode.OK)

        if (!Role.validateRole(input.authInput.role))
            return User(status = ExitCode.ROLE_UNKNOWN)

        if (!Validator.validateResource(input.authInput.resource))
            return User(status = ExitCode.ACCESS_DENIED)

        val resources = authService.findResByLoginAndRole(input.authInput.login, Role.valueOf(input.authInput.role))

        return if (isChild(input.authInput.resource, resources)) {
            provider.provide(input)
        } else
            User(status = ExitCode.ACCESS_DENIED)
    }
}