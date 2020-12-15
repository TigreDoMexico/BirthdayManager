package com.david.birthdaymanager.activity.views

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.david.birthdaymanager.R
import com.david.birthdaymanager.activity.constants.ACTIVITY_REPLY
import com.david.birthdaymanager.business.BirthdayBusiness
import kotlinx.android.synthetic.main.activity_add_birthday.*
import java.util.*

class AddBirthdayActivity : AppCompatActivity() {
    var birthdayName: String = ""
    var birthdayDate: Calendar? = null
    var birthdayValue: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_birthday)

        val dialog = datePickerDialog()

        date_picker.setOnClickListener{
            dialog.show()

            val year = dialog.findViewById<View>(Resources.getSystem().getIdentifier("android:id/year", null, null))
            if(year != null){
                year.visibility = View.GONE
            }
        }

        create_birthday.setOnClickListener{
            onSubmitFormData()
        }
    }

    private fun onSubmitFormData() {
        val replyIntent = Intent()
        birthdayName = birthday_name.text.toString()

        if (birthdayDate == null && birthdayName.isEmpty()) {
            setResult(RESULT_CANCELED, replyIntent)
        } else {
            val birthday = BirthdayBusiness(birthdayName, birthdayValue)
            replyIntent.putExtra(ACTIVITY_REPLY, birthday)
            setResult(RESULT_OK, replyIntent)
        }

        finish()
    }

    @SuppressLint("SetTextI18n")
    fun datePickerDialog(): DatePickerDialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //Date Picker Dialog
        val datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
            birthdayDate = GregorianCalendar(year, monthOfYear, dayOfMonth)
            birthdayValue = "$dayOfMonth/${monthOfYear + 1}"

            date_picked.text = birthdayValue
        }, year, month, day)

        return datePickerDialog
    }
}