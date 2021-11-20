package application.main.dto

data class UserDTO(val login: String, val password: String, val salt: String = "")