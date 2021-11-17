package application.main.input

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType

class Input(args: Array<String>) {
    private val parser = ArgParser("simple application")

    val login: String? by parser.option(ArgType.String, shortName = "login", description = "user name")
    val password: String? by parser.option(ArgType.String, shortName = "pass", description = "user password")
    val role: String? by parser.option(ArgType.String, shortName = "role", description = "access level")
    val resource: String? by parser.option(ArgType.String, shortName = "res", description = "needed resource")
    val startDate: String? by parser.option(ArgType.String, shortName = "ds", description = "using application start time")
    val endDate: String? by parser.option(ArgType.String, shortName = "de", description = "using app finish time")
    val volume: String? by parser.option(ArgType.String, shortName = "vol", description = "resource volume")

    val identityInput: IdentityInput?
    val authInput: AuthInput?
    val accountInput: AccountInput?

    init {
        parser.parse(if (args.isEmpty()) arrayOf("-h") else args)

        identityInput = if (login != null && password != null) IdentityInput(login!!, password!!) else null
        authInput = if (role != null && resource != null) AuthInput(login!!, role!!, resource!!) else null
        accountInput = if (startDate != null && endDate != null && volume != null) AccountInput(startDate!!, endDate!!, volume!!) else null
    }
}
