package application.main.services

import application.main.dto.UserDTO
import application.main.dto.UserData

class UserService {

    fun findUserByLogin(login: String): UserDTO? {
        val loginToPass = UserData.userToPass.find { login == it.login } ?: return null
        val salt = Encoder.saltGen()

        return UserDTO(loginToPass.password, Encoder.encodePassword(loginToPass.password, salt), salt)
    }
}