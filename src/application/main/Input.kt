package application.main

data class Input(
    val hasEcho: Boolean,
    val login: String?,
    val password: String?,
    val resource: String?,
    val role: String?,
    val startDate: String?,
    val endDate: String?,
    val value: String?
)
