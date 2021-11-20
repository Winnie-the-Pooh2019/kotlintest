package application.main.userdata.storage

import application.main.userdata.Role

object UserAuthData {
    val usersToRolesToRes = listOf(
        RoleResourceDTO("Ivan", Role.READ, "A.B.C"),
        RoleResourceDTO("Billy", Role.WRITE, "A.B"),
        RoleResourceDTO("Koala", Role.EXECUTE, "A.B.C.D"),
        RoleResourceDTO("jdoe", Role.READ, "a"),
        RoleResourceDTO("jdoe",Role.WRITE, "a.b"),
        RoleResourceDTO("jrow", Role.EXECUTE, "a.b.c"),
        RoleResourceDTO("jdoe", Role.EXECUTE, "a.bc")
    )
}