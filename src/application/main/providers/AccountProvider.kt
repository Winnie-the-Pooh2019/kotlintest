package application.main.providers

import application.main.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.userdata.Role
import java.time.LocalDate

class AccountProvider : IProvider {

    override fun provide(input: Input): User {
        val pairData: Pair<LocalDate, LocalDate>

        try {
            pairData = Validator.validateDates(input.startDate!!, input.endDate!!)
        } catch (e: Exception) {
            return User(input.login, Role.valueOf(input.role!!), input.resource, status = ExitCode.SUSPICIOUS_ACTIVITY)
        }

        if(!Validator.validateValue(input.volume!!))
            return User(input.login, Role.valueOf(input.role!!), input.resource, status = ExitCode.SUSPICIOUS_ACTIVITY)

        return User(
            input.login,
            Role.valueOf(input.role!!),
            input.resource,
            pairData.first,
            pairData.second,
            input.volume!!.toInt(),
            ExitCode.SUCCESS)
    }
}