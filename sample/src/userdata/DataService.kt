package userdata

import userdata.storage.RoleData
import userdata.storage.UserDTO
import userdata.storage.UserData
import exceptions.ElementAlreadyExists
import exceptions.NoSuchLoginException

class DataService : Service {

    private val userData: UserData = UserData()
    private val roleData = RoleData()

    init {
        for (i in userData.userNamesSupport.indices) {
            addUserData(userData.userNamesSupport[i], userData.userPassSupport[i])
        }
    }

    @Throws(NoSuchElementException::class, NoSuchLoginException::class)
    override fun receiveUserData(login: String): UserDTO {
        if (!userData.users.any { it.login == login })
            throw NoSuchLoginException("There is no login: $login")

        return userData.users.first { it.login == login }
    }

    @Throws(NoSuchElementException::class)
    override fun receiveUserRoles(login: String) = roleData.rolesSuspected.getValue(login)

    @Throws(ElementAlreadyExists::class)
    override fun addUserData(login: String, notEncodedPass: String) {
        if (userData.users.any { it.login == login })
            throw ElementAlreadyExists("User with login: $login already exists in users' database")

        val salt = Encoder.saltGen()

        userData.users.add(
            UserDTO(
                login,
                Encoder.encode(Encoder.encode(notEncodedPass) + salt),
                salt
            )
        )
    }

    @Throws(ElementAlreadyExists::class)
    override fun addUserRole(login: String, role: Role) {
        if (roleData.rolesSuspected.any { it.key == login })
            throw ElementAlreadyExists("User with login: $login already exists roles' in database")

        roleData.rolesSuspected[login] = role
    }
}