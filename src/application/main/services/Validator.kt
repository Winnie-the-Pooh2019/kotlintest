package application.main.services

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.regex.Pattern

object Validator {
    private val loginPattern = Pattern.compile("^[a-zA-Z0-9]{1,20}$") //(?=.*[a-zA-Z])(?=.*[0-9])(^[a-zA-Z0-9]{1,20}$)
    private val valuePattern = Pattern.compile("^[0-9]+$")
    private val resourcePattern = Pattern.compile("^[a-zA-Z]{1,10}$")

    fun validateLogin(login: String) = loginPattern.matcher(login).find()

    fun validateDates(start: String, end: String): Boolean {
        val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return try {
            val startTime = LocalDate.from(dtf.parse(start))
            val endTime = LocalDate.from(dtf.parse(end))

            startTime <= endTime
        } catch (dte: DateTimeParseException) {
            false
        }
    }

    fun validateValue(value: String) = valuePattern.matcher(value).matches()

    fun validateResource(resource: String) = resource.split(".").all { resourcePattern.matcher(it).matches() }
}