package application.main.services

import application.main.userdata.Role

interface IAuthService {
    fun findResByLoginAndRole(login: String, role: Role): List<String>
}