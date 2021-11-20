package application.main.domain

enum class ExitCode(val code: Int) {
    OK(0),
    LOGIN_FORMAT_INCORRECT(2),
    LOGIN_INCORRECT(3),
    PASSWORD_INCORRECT(4),
    ROLE_UNKNOWN(5),
    ACCESS_DENIED(6),
    SUSPICIOUS_ACTIVITY(7)
}