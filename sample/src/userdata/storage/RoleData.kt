package userdata.storage

import userdata.Role

class RoleData {
    val rolesSuspected = mutableMapOf(
        "Ivan" to Role.READ,
        "Billy" to Role.WRITE,
        "Koala" to Role.EXECUTE
    )
}