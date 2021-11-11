package application.main

import application.main.userdata.Role
import java.util.*

data class User(
    var login: String? = null,

    var role: Role? = null,
    var resource: String? = null,

    var startDate: Date? = null,
    var endDate: Date? = null,
    var volume: Int? = null
)