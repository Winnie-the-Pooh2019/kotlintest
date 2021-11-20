package application.main.services

import application.main.domain.Input
import application.main.domain.ExitCode

interface IProvider {
    fun provide(input: Input): ExitCode
}