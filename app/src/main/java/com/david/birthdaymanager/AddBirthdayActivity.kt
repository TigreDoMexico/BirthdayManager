package com.david.birthdaymanager

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.david.birthdaymanager.data.Birthday
import kotlinx.android.synthetic.main.activity_add_birthday.*
import java.util.*

class AddBirthdayActivity : AppCompatActivity() {
    var birthdayName: String = "";
    var birthdayDate: Calendar? = null;

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
            birthdayName = birthday_name.text.toString()

            if(birthdayDate != null && birthdayName.isNotEmpty()){
                var birthday = Birthday(birthdayName, birthdayDate!!)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun datePickerDialog(): DatePickerDialog {
        val c =  Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //Date Picker Dialog
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, year, monthOfYear, dayOfMonth ->
            birthdayDate = GregorianCalendar(year, monthOfYear, dayOfMonth)
        }, year, month, day)

        return datePickerDialog
    }
}