package application.main.services

import application.main.userdata.storage.UserDTO
import application.main.userdata.storage.UserData

class UserService {

    fun findUserByLogin(login: String): UserDTO? {
        val loginToPass = UserData.userToPass.find { login == it.login } ?: return null
        val salt = Encoder.saltGen()

        return UserDTO(loginToPass.password, Encoder.hash(loginToPass.password, salt), salt)
    }
}