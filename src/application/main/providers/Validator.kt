package application.main.providers

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Pattern

object Validator {
    fun validateLogin(login: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9]{1,20}$")

        return pattern.matcher(login).find()
    }

    fun validateDates(start: String, end: String): Pair<LocalDate, LocalDate> {
        val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val startTime = LocalDate.from(dtf.parse(start))
        val endTime = LocalDate.from(dtf.parse(end))

        if (startTime > endTime)
            throw ParseException("dates don't meet the requirement", 0)

        return Pair<LocalDate, LocalDate>(startTime, endTime)
    }

    fun validateValue(value: String): Boolean {
        val pattern = Pattern.compile("^[0-9]+$")

        return pattern.matcher(value).find()
    }

    fun validateResource(resource: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z]{1,10}$")

        return resource.split(".").all { pattern.matcher(it).find() }
    }
}