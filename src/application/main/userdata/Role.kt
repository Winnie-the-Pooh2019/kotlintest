package application.main.userdata

enum class Role {
    READ,
    WRITE,
    EXECUTE;

    companion object {
        fun validateRole(suspectedRole: String) = suspectedRole in values().map { it.name }
    }
}