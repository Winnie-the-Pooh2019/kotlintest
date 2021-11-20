package application.main.providers

import application.main.input.Input
import application.main.providers.exitcodes.ExitCode

interface IProvider {
    fun provide(input: Input): ExitCode
}