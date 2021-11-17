package application.main

import application.main.providers.exitcodes.ExitCode
import application.main.userdata.Role
import java.time.LocalDate

/**
 * @author Winnie-thePooh2019
 *
 * Совйства класса могут быть null, потому что объект User, возвращаемый методом
 * identityProvide(), может быть заполненным не до конца
 */
data class User(
    val login: String? = null,

    val role: Role? = null,
    val resource: String? = null,

    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val volume: Int? = null,

    val status: ExitCode
)