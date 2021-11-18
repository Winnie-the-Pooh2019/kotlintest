package application.main.userdata.storage

import application.main.userdata.Role

object UserAuthData {
    val usersToRolesToRes = listOf(
        listOf("Ivan", Role.READ.name, "A.B.C"),
        listOf("Billy", Role.WRITE.name, "A.B"),
        listOf("Koala", Role.EXECUTE.name, "A.B.C.D"),
        listOf("jdoe", Role.READ.name, "a"),
        listOf("jdoe",Role.WRITE.name, "a.b"),
        listOf("jrow", Role.EXECUTE.name, "a.b.c"),
        listOf("jdoe", Role.EXECUTE.name, "a.bc")
    )
}