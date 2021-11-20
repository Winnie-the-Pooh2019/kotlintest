package application.main.services

import application.main.domain.Input
import application.main.domain.ExitCode

class AccountProvider : IProvider {

    override fun provide(input: Input): ExitCode {
        if (!input.accountInput.exists)
            return ExitCode.OK

        try {
            if (!Validator.validateDates(input.accountInput.startDate, input.accountInput.endDate))
                return ExitCode.SUSPICIOUS_ACTIVITY
        } catch (e: Exception) {
            return ExitCode.SUSPICIOUS_ACTIVITY
        }

        if(!Validator.validateValue(input.accountInput.volume))
            return ExitCode.SUSPICIOUS_ACTIVITY

        return ExitCode.OK
    }
}