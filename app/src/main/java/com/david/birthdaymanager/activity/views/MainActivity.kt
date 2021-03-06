package com.david.birthdaymanager.activity.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.david.birthdaymanager.BirthdayApplication
import com.david.birthdaymanager.R
import com.david.birthdaymanager.activity.adapters.BirthdayAdapter
import com.david.birthdaymanager.activity.constants.ACTIVITY_REPLY
import com.david.birthdaymanager.activity.decorators.SpaceDecorator
import com.david.birthdaymanager.business.BirthdayBusiness
import com.david.birthdaymanager.viewmodel.BirthdayModelFactory
import com.david.birthdaymanager.viewmodel.BirthdayViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val newBirthdayActivityRequestCode = 1
    private val birthdayViewModel: BirthdayViewModel by viewModels {
        BirthdayModelFactory((application as BirthdayApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_birthday.setOnClickListener(this)

        val adapter = createBirthdayAdapter()



        birthdayViewModel.allBirthdays.observe(owner = this) { birthday ->
            birthday.let { adapter.submitList(it) }
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, AddBirthdayActivity::class.java)
        startActivityForResult(intent, newBirthdayActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newBirthdayActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val result = data?.getSerializableExtra(ACTIVITY_REPLY) as? BirthdayBusiness
            val birthday = result?.createData()!!

            birthdayViewModel.insert(birthday)
        }
    }

    private fun createBirthdayAdapter(): BirthdayAdapter {
        val adapter = BirthdayAdapter()
        birthday_list.adapter = adapter
        birthday_list.layoutManager = LinearLayoutManager(this)
        birthday_list.addItemDecoration(SpaceDecorator(10))
        return adapter
    }
}

