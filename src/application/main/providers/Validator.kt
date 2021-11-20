package application.main.providers

import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

object Validator {
    private val loginPattern = Pattern.compile("^[a-zA-Z0-9]{1,20}$") //(?=.*[a-zA-Z])(?=.*[0-9])(^[a-zA-Z0-9]{1,20}$)
    private val valuePattern = Pattern.compile("^[0-9]+$")
    private val resourcePattern = Pattern.compile("^[a-zA-Z]{1,10}$")

    fun validateLogin(login: String) = loginPattern.matcher(login).find()

    fun validateDates(start: String, end: String): Boolean {
        val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val startTime = LocalDate.from(dtf.parse(start))
        val endTime = LocalDate.from(dtf.parse(end))

        if (startTime > endTime)
            return false

        return true
    }

    fun validateValue(value: String) = valuePattern.matcher(value).matches()

    fun validateResource(resource: String) = resource.split(".").all { resourcePattern.matcher(it).matches() }
}