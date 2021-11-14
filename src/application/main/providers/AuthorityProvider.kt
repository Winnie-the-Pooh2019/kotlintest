package application.main.providers

import application.main.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.services.AuthService
import application.main.userdata.Role
import java.util.regex.Pattern

class AuthorityProvider {
    private val authService = AuthService()
    private val accProvider = AccountProvider()

    fun resourceProvide(input: Input): Pair<User, ExitCode> {
        if (!Validator.validateRole(input.role!!))
            return Pair(User(input.login), ExitCode.ROLE_UNKNOWN)

        if (!Validator.validateResource(input.resource!!))
            return Pair(User(input.login), ExitCode.ACCESS_DENIED)

        val resources = authService.findResByLoginAndRole(input.login!!, Role.valueOf(input.role!!))

        return if (resources.isEmpty() || isChild(input.resource!!, resources)) {
            if (input.startDate != null && input.endDate != null && input.volume != null)
                accProvider.accountProvide(input)
            else
                Pair(User(input.login, Role.valueOf(input.role!!), input.resource), ExitCode.SUCCESS)
        } else
            Pair(User(input.login), ExitCode.ACCESS_DENIED)
    }

    private fun isChild(resource: String, resources: List<String>): Boolean {
        return resources.any {
            println("$resource | $it")
            val pattern = Pattern.compile("^$it(\\.[a-zA-Z]{1,10})*")

            return@any pattern.matcher(resource).find()
        }
    }
}