package com.david.birthdaymanager.activity.views

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.david.birthdaymanager.R
import com.david.birthdaymanager.business.BirthdayBusiness
import kotlinx.android.synthetic.main.activity_add_birthday.*
import java.util.*

class AddBirthdayActivity : AppCompatActivity() {
    var birthdayName: String = "";
    var birthdayDate: Calendar? = null;
    var birthdayValue: String = "";

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
            val replyIntent = Intent()
            birthdayName = birthday_name.text.toString()

            if(birthdayDate == null && birthdayName.isEmpty()){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val birthday = BirthdayBusiness(birthdayName, birthdayValue)
                replyIntent.putExtra(EXTRA_REPLY, birthday)
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }

    }

    @SuppressLint("SetTextI18n")
    fun datePickerDialog(): DatePickerDialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //Date Picker Dialog
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, year, monthOfYear, dayOfMonth ->
            birthdayDate = GregorianCalendar(year, monthOfYear, dayOfMonth)
            birthdayValue = "$monthOfYear/$year"

            date_picked.text = birthdayValue
        }, year, month, day)

        return datePickerDialog
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.birthdaylist.REPLY"
    }
}