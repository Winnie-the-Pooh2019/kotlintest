package application.main.providers.exitcodes

enum class ExitCode {
    SUCCESS,
    HELP,
    LOGIN_FORMAT_INCORRECT,
    LOGIN_INCORRECT,
    PASSWORD_INCORRECT,
    ROLE_UNKNOWN,
    ACCESS_DENIED,
    SUSPICIOUS_ACTIVITY
}