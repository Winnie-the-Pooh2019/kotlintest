package application.main.services

import application.main.domain.Input
import application.main.domain.ExitCode

class AccountProvider : IProvider {

    override fun provide(input: Input): ExitCode {
        if (input.startDate == null || input.endDate == null || input.volume == null)
            return ExitCode.OK

        return if (Validator.validateValue(input.volume) && Validator.validateDates(input.startDate, input.endDate))
            ExitCode.OK
        else
            ExitCode.SUSPICIOUS_ACTIVITY
    }
}