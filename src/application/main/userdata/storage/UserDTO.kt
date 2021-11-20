package application.main.userdata.storage

data class UserDTO(val login: String, val password: String, val salt: String = "")