package application.main.services

import application.main.userdata.storage.UserDTO
import application.main.userdata.storage.UserData

class UserService {

    private val userData: UserData = UserData()

    init {
        for (i in userData.userNamesSupport.indices) {
            insertUser(userData.userNamesSupport[i], userData.userPassSupport[i])
        }
    }

    fun findUserByLogin(login: String): UserDTO? {

        return userData.users.find { it.login == login }
    }

    fun insertUser(login: String, notEncodedPass: String) {
        if (userData.users.any { it.login == login })
            throw Exception("User with login: $login already exists in users' database")

        val salt = Encoder.saltGen()

        userData.users.add(
            UserDTO(
                login,
                Encoder.encode(Encoder.encode(notEncodedPass) + salt),
                salt
            )
        )
    }

    fun insertUser(userDTO: UserDTO) {
        if (findUserByLogin(userDTO.login) != null)
            throw Exception("User with login: ${userDTO.login} already exists in users' database")

        userDTO.salt = Encoder.saltGen()

        userDTO.password = Encoder.encode(Encoder.encode(userDTO.password) + userDTO.salt)

        userData.users.add(userDTO)
    }
}