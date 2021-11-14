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

    fun findUserByLogin(login: String): UserDTO? = userData.users.find { it.login == login }

    private fun insertUser(login: String, notEncodedPass: String) {
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
}