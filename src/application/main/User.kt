package application.main

import application.main.userdata.Role
import java.time.LocalDate

data class User(
    var login: String? = null,

    var role: Role? = null,
    var resource: String? = null,

    var startDate: LocalDate? = null,
    var endDate: LocalDate? = null,
    var volume: Int? = null
)