package services

import userdata.Role
import userdata.storage.UserDTO

interface IUserService {
    fun findUserByLogin(login: String): UserDTO?

    fun insertUser(login: String, notEncodedPass: String)

    fun insertUser(userDTO: UserDTO)
}