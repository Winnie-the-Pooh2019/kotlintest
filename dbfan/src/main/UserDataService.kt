package main

import java.sql.ResultSet

class UserDataService {

    fun getUserByLogin(login: String): User {
        val query = "select * from users where login=?;"

        val statement = DataDefiner.connection.prepareStatement(query)
        statement.setString(1, login)

        return toUser(statement.executeQuery())
    }

    fun getRoleByLogin(login: String) : Role? {
        val query = "select roles.NAME from ROLES " +
                "inner join USER_ROLES UR on ROLES.ID = UR.ROLE_ID " +
                "inner join USERS U on U.ID = UR.USER_ID where LOGIN = ?"

        val statement = DataDefiner.connection.prepareStatement(query)
        statement.setString(1, login)

        return toRole(statement.executeQuery())
    }

    fun insertUser(user: User) {
        val query = "insert into users (PASSWORD, LOGIN) VALUES ( ?, ? )"

        val statement = DataDefiner.connection.prepareStatement(query)
        statement.setString(1, user.password)
        statement.setString(2, user.login)

        statement.execute()
    }

    fun insertUserRole(user: User, role: Role) {
        val query = "insert into USER_ROLES (USER_ID, ROLE_ID) VALUES ( " +
                "select id from USERS where LOGIN=?, " +
                "select id from ROLES where NAME=?);"

        val statement = DataDefiner.connection.prepareStatement(query)
        statement.setString(1, user.login)
        statement.setString(2, role.name.uppercase())

        statement.execute()
    }

    private fun toUser(resultSet: ResultSet): User {
        resultSet.next()
        return User(resultSet.getString("login"), resultSet.getString("password"))
    }

    private fun toRole(resultSet: ResultSet): Role? {
        resultSet.next()
        return if (resultSet.getString("name") in Role.values().map { it.name.uppercase() })
            Role.valueOf(resultSet.getString("name"))
        else
            null
    }
}