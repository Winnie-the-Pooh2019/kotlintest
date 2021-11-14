package application.main.providers

import application.main.Input
import application.main.User
import application.main.providers.exitcodes.ExitCode
import application.main.userdata.Role
import java.text.ParseException
import java.util.*

class AccountProvider {

    fun accountProvide(input: Input): Pair<User, ExitCode> {
        val pairData: Pair<Date, Date>

        try {
            pairData = Validator.validateDates(input.startDate!!, input.endDate!!)
        } catch (e: ParseException) {
            return Pair(User(input.login, Role.valueOf(input.role!!), input.resource), ExitCode.SUSPICIOUS_ACTIVITY)
        }

        if(!Validator.validateValue(input.volume!!))
            return Pair(User(input.login, Role.valueOf(input.role!!), input.resource), ExitCode.SUSPICIOUS_ACTIVITY)

        return Pair(User(
            input.login,
            Role.valueOf(input.role!!),
            input.resource,
            pairData.first,
            pairData.second,
            input.volume!!.toInt()
        ), ExitCode.SUCCESS)
    }
}