package userdata

import userdata.storage.UserDTO

interface Service {
    fun receiveUserData(login: String): UserDTO

    fun receiveUserRoles(login: String): Role

    fun addUserData(login: String, notEncodedPass: String)

    fun addUserRole(login: String, role: Role)
}