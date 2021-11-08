### Структура
1) Блок аутентификации:
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
    fun identityProvide(login: String, password: String): Boolean
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
    fun resourceProvide(login: String, role: Role, resource: String): Boolean
}
```
3) метод для вывода справки:
```kotlin
fun echo()
```
4) класс-хранилище входных данных пользователя
```kotlin
 data class Input(
    val hasEcho: Boolean,
    val login: String?,
    val password: String?,
    val resource: String?,
    val role: String?,
    val startDate: String?,
    val endDate: String?,
    val value: String?)
```
5) объект ```Validator``` с методами валидации входных данных
6) Аккаунтинг:
* Интерфейс, описывающий методы для аккаутинга:
```kotlin
interface IAccountProvider {
    //todo describe accounting methods
}
```