package application.main.services

import application.main.domain.Input
import application.main.domain.ExitCode
import application.main.domain.Role

class AuthorityProvider(private val provider: IProvider, private val authService: AuthService) : IProvider {
    private fun isChild(resource: String, resources: List<String>) = resources.any { resource.contains("$it.") || resource == it }

    override fun provide(input: Input): ExitCode {
        if (input.login == null || input.role == null || input.resource == null)
            return ExitCode.OK

        if (!Role.validateRole(input.role))
            return ExitCode.ROLE_UNKNOWN

        if (!Validator.validateResource(input.resource))
            return ExitCode.ACCESS_DENIED

        val resources = authService.findResByLoginAndRole(input.login, Role.valueOf(input.role))

        return if (isChild(input.resource, resources)) {
            provider.provide(input)
        } else
            ExitCode.ACCESS_DENIED
    }
}