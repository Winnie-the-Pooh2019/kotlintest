package application.main.providers

import application.main.User

class AccountProvider:IAccountProvider {

    override fun accountProvide(startDate: String, endDate: String, value: String): User {
        val userObj = User()
        val pairDatas = Validator.validateDates(startDate, endDate)
        userObj.startDate = pairDatas.first
        userObj.endTime = pairDatas.second

        if(!Validator.validateValue(value)){
            throw Exception (" value dont work")
        }

        userObj.value = value.toInt()
        return userObj
    }
}