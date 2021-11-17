package application.main.providers

import application.main.Input
import application.main.User

interface IProvider {
    fun provide(input: Input): User
}