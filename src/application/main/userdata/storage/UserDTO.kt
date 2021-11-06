package application.main.userdata.storage

data class UserDTO(val login: String, var password: String, var salt: String?)
