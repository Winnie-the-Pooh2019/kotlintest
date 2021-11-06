package application.main.services

import application.main.userdata.storage.UserDTO

interface IUserService {
    fun findUserByLogin(login: String): UserDTO?

    fun insertUser(login: String, notEncodedPass: String)

    fun insertUser(userDTO: UserDTO)
}