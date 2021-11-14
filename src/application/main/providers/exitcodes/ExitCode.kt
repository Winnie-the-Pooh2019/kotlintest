package application.main.providers.exitcodes

enum class ExitCode(val code: Int) {
    SUCCESS(0),
    HELP(1),
    LOGIN_FORMAT_INCORRECT(2),
    LOGIN_INCORRECT(3),
    PASSWORD_INCORRECT(4),
    ROLE_UNKNOWN(5),
    ACCESS_DENIED(6),
    SUSPICIOUS_ACTIVITY(7)
}