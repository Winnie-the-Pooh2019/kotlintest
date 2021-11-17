package application.main.providers

import application.main.Input
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
            val pattern = Pattern.compile("^$it(\\.[a-zA-Z]{1,10})*")

            return@any pattern.matcher(resource).find()
        }
    }

    override fun provide(input: Input): User {
        if (!Role.validateRole(input.role!!))
            return User(input.login, status = ExitCode.ROLE_UNKNOWN)

        if (!Validator.validateResource(input.resource!!))
            return User(input.login, status = ExitCode.ACCESS_DENIED)

        val resources = authService.findResByLoginAndRole(input.login!!, Role.valueOf(input.role!!))

        return if (resources.isNotEmpty() || isChild(input.resource!!, resources)) {
            if (input.startDate != null && input.endDate != null && input.volume != null)
                provider.provide(input)
            else
                User(input.login, Role.valueOf(input.role!!), input.resource, status = ExitCode.SUCCESS)
        } else
            User(input.login, status = ExitCode.ACCESS_DENIED)
    }
}