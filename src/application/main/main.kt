package application.main

import application.main.providers.IdentityProvider
import application.main.providers.exitcodes.ExitCode
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if ("-h" in args) {
        showFaq()
        exitProcess(1)
    }

//    args.forEach(::println)

    val input = Input(args)

//    println("login = ${input.login} | ")

    val provider = IdentityProvider()

    val userToExit = if (input.login != null && input.password != null)
        provider.identityProvide(input)
    else
        Pair(User(), ExitCode.HELP)

    when (userToExit.second) {
        ExitCode.ACCESS_DENIED -> exitProcess(ExitCode.ACCESS_DENIED.ordinal)

        ExitCode.LOGIN_FORMAT_INCORRECT -> exitProcess((ExitCode.LOGIN_FORMAT_INCORRECT.ordinal))

        ExitCode.LOGIN_INCORRECT -> exitProcess(ExitCode.LOGIN_INCORRECT.ordinal)

        ExitCode.PASSWORD_INCORRECT -> exitProcess(ExitCode.PASSWORD_INCORRECT.ordinal)

        ExitCode.ROLE_UNKNOWN -> exitProcess(ExitCode.ROLE_UNKNOWN.ordinal)

        ExitCode.SUCCESS -> exitProcess(ExitCode.SUCCESS.ordinal)

        ExitCode.SUSPICIOUS_ACTIVITY -> exitProcess(ExitCode.SUSPICIOUS_ACTIVITY.ordinal)

        ExitCode.HELP -> {
            showFaq()
            exitProcess(ExitCode.HELP.ordinal)
        }
    }
}

fun showFaq() {
    println(
        "\n-login ' Ввести логин '" +
                "\n-pass ' Ввести пароль '" +
                "\n-res ' Cтрока из букв по уровням, резделенными точками '" +
                "\n-role ' Роль (Read, Write, Execute '" +
                "\n-ds ' Строка даты ГГГГ.ММ.ДД '" +
                "\n-de ' Строка даты ГГГГ.ММ.ДД '" +
                "\n-val ' Число (целое) '" +
                "\n-h ' Справка '"
    )
}