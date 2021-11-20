package application.main.domain

data class AuthInput(val login: String = "", val role: String = "", val resource: String = "", val exists: Boolean = true)