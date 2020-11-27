package com.david.birthdaymanager.business

import com.david.birthdaymanager.data.Birthday
import java.io.Serializable

class BirthdayBusiness(
    val name: String,
    val date: String
) : Serializable {

    fun createData(): Birthday {
        return Birthday(name = name, date = date)
    }
}