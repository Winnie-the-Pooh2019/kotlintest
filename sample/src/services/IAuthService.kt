package services

import userdata.Role

interface IAuthService {
    fun findResByLoginAndRole(login: String, role: Role): List<String>

//    fun0000000001234856541879643541
}