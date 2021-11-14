package application.main.services

import application.main.userdata.storage.UserDTO
import application.main.userdata.storage.UserData

class UserService {

    init {
        for (i in UserData.userNamesSupport.indices) {
            insertUser(UserData.userNamesSupport[i], UserData.userPassSupport[i])
        }
    }

    fun findUserByLogin(login: String): UserDTO? = UserData.users.find { it.login == login }

    private fun insertUser(login: String, notEncodedPass: String) {
        if (UserData.users.any { it.login == login })
            throw Exception("User with login: $login already exists in users' database")

        val salt = Encoder.saltGen()

        UserData.users.add(
            UserDTO(
                login,
                Encoder.encode(Encoder.encode(notEncodedPass) + salt),
                salt
            )
        )
    }
}