package main

fun main() {
    val userDataService = UserDataService()

//    val user = userDataService.getUserByLogin("igor")
//    println(user)
    val userRole = userDataService.getRoleByLogin("igor")
    println(userRole)
}