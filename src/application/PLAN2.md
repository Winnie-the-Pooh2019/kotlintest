### Структура
1) Блок аутентификации:
* класс ```Input``` с делегированными kotlinx-cli полями 
* перечисление кодов выхода из программы:
```kotlin
enum class ExitCode {
    SUCCESS,
    HELP,
    LOGIN_FORMAT_INCORRECT,
    LOGIN_INCORRECT,
    PASSWORD_INCORRECT,
    ROLE_UNKNOWN,
    ACCESS_DENIED,
    SUSPICIOUS_ACTIVITY
}
```
* класс ```UserData```, хранящий в отедльных массивах логины предполагаемых пользователей и их пароли в сыром виде, а так же массив объектов ```UserDTO```.
* дата-класс для "обертки" данных пользователей:
```kotlin
data class UserDTO(
   val login: String, 
   var password: String, 
   var salt: String?)
```
* класс с публичными методами для шифровки пароля и генерации соля для пользователя соответственно:
```kotlin
object Encoder {
    fun encode(source: String): String

    fun saltGen(): String
}
```
* интерфейс ```IUserService```, описывающий главные методы для доступа к аутентификационным данным пользователей:
```kotlin
interface IUserService {
   fun findUserByLogin(login: String): UserDTO?

   fun insertUser(login: String, notEncodedPass: String)

   fun insertUser(userDTO: UserDTO)
}
```
* интерфейс, описывающий методы для проверки введеных пользователем данных:
```kotlin
interface IIdentityProvider {
    fun identityProvide(input: Input): Pair<User, ExitCode>
}
```
2) Блок авторизации
* перечисление ролей:
```kotlin
enum class Role {
    READ,
    WRITE,
    EXECUTE
}
```
* интерфейс, описывающий методы для получения авторизацонных данных пользователей:
```kotlin
interface IAuthService {
    fun findResByLoginAndRole(login: String, role: Role): List<String>
}
```
* интерфейс с методами для проведения авторизации:
```kotlin
interface IAuthorityProvider {
    fun resourceProvide(input: Input): Pair<User, ExitCode>
}
```
3) метод для вывода справки:
```kotlin
fun echo()
```
4) объект ```Validator``` с методами валидации входных данных
5) Аккаунтинг:
* Интерфейс, описывающий методы для аккаутинга:
```kotlin
interface IAccountProvider {
    fun accountProvide(input: Input): Pair<User, ExitCode>
}
```
6) класс-валидатор входных данных
```kotlin
object Validator {
    fun validateLogin(login: String): Boolean

    fun validateDates(start: String, end: String): Pair<Date, Date>

    fun validateValue(value: String): Boolean

    fun validateRole(suspectedRole: String): Boolean

    fun validateResource(resource: String): Boolean
}
```
7) класс пользователя, хранящий состояние о деятельности пользователя:
```kotlin
data class User(
    var login: String? = null,

    var role: Role? = null,
    var resource: String? = null,

    var startDate: Date? = null,
    var endDate: Date? = null,
    var volume: Int? = null
)
```