package application.main.providers

import application.main.User

interface IAccountProvider {
    fun accountProvide(startDate: String, endDate: String, value: String): User?
}
