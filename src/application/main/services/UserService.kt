package application.main.services

import application.main.userdata.storage.UserDTO
import application.main.userdata.storage.UserData

class UserService {

    fun findUserByLogin(login: String): UserDTO? {
        val loginToPass = UserData.userToPass.find { login == it[0] } ?: return null
        val salt = Encoder.saltGen()

        return UserDTO(loginToPass[0], Encoder.encode(Encoder.encode(loginToPass[1]) + salt), salt)
    }
}