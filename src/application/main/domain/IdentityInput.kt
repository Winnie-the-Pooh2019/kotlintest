package application.main.domain

data class IdentityInput(val login: String = "", val password: String = "", val exists: Boolean = true)