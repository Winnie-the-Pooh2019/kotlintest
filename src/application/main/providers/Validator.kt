package application.main.providers

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object Validator {
    fun validateLogin(login: String): Boolean {
        val pattern = Pattern.compile("[a-zA-Z0-9]*")

        return pattern.matcher(login).find()
    }

    fun validateDates(start: String, end: String): Pair<Date, Date> {
        val dtf = SimpleDateFormat("yyyy-MM-dd")
        dtf.isLenient = false

        val startTime = dtf.parse(start)
        val endTime = dtf.parse(end)

        if (startTime > endTime)
            throw ParseException("dates don't meet the requirement", 0)

        return Pair<Date, Date>(startTime, endTime)
    }

    fun validateValue(value: String): Boolean {
        val pattern = Pattern.compile("[0-9]*")

        return pattern.matcher(value).find()
    }

    fun validateResource(resource: String): Boolean {
        val pattern = Pattern.compile("[a-zA-Z]{1,10}")

        return pattern.matcher(resource).find()
    }
}