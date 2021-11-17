package application.main.providers

import application.main.input.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.userdata.Role
import java.time.LocalDate

class AccountProvider : IProvider {

    override fun provide(input: Input): User {
        if (input.accountInput == null)
            return User(input.authInput!!.login, Role.valueOf(input.authInput.role), input.authInput.resource, status = ExitCode.OK)

        val pairData: Pair<LocalDate, LocalDate>

        try {
            pairData = Validator.validateDates(input.accountInput.startDate, input.accountInput.endDate)
        } catch (e: Exception) {
            return User(status = ExitCode.SUSPICIOUS_ACTIVITY)
        }

        if(!Validator.validateValue(input.accountInput.volume))
            return User(status = ExitCode.SUSPICIOUS_ACTIVITY)

        return User(
            input.authInput!!.login,
            Role.valueOf(input.authInput.role),
            input.authInput.resource,
            pairData.first,
            pairData.second,
            input.accountInput.volume.toInt(),
            ExitCode.OK)
    }
}