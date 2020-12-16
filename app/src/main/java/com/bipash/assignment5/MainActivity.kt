package com.bipash.assignment5

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var txtJoinedYear : EditText
    private lateinit var txtDOB :EditText
    private lateinit var txtServedYear : TextView
    private lateinit var txtAge : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtJoinedYear = findViewById(R.id.txtJoinedDate)
        txtServedYear = findViewById(R.id.txtServedYear)
        txtDOB = findViewById(R.id.txtDOB)
        txtAge = findViewById(R.id.txtAge)

        txtJoinedYear.setOnClickListener {
            loadCalender()
        }
        txtDOB.setOnClickListener {
            loadCalender(20)
        }
    }

    private fun loadCalender(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                txtJoinedYear.setText("Selected Year : ${dayOfMonth}/${monthOfYear + 1}/${year}")
                var served = calculateTimePeriod(year, month, day)
                txtServedYear.text = "${served}Yrs"
            },
            year - 3,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun loadCalender(DOB: Int){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                txtDOB.setText("Selected Year : ${dayOfMonth}/${monthOfYear+1}/${year}")
                var age = calculateTimePeriod(year, month, day)
                txtAge.text = "${age}Yrs"
            },
            year - DOB,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun calculateTimePeriod(year: Int, month: Int, day: Int): String? {
        val userInput = Calendar.getInstance()
        val present = Calendar.getInstance()
        userInput[year, month] = day
        var timePeriod = present[Calendar.YEAR] - userInput[Calendar.YEAR]
        if (present[Calendar.DAY_OF_YEAR] < userInput[Calendar.DAY_OF_YEAR]) {
            timePeriod--
        }
        val timePeriodInt = timePeriod
        return timePeriodInt.toString()
    }
}