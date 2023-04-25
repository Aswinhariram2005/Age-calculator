package com.aswinhariram2005.agecalculator

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.aswinhariram2005.agecalculator.databinding.ActivityMainBinding
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.datePicBtn.setOnClickListener{
            open_datePicker(it)
        }


    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun open_datePicker(it: View?) {
        var cal = Calendar.getInstance()
        var y = cal.get(Calendar.YEAR)
        var m = cal.get(Calendar.MONTH)
        var DoM = cal.get(Calendar.DAY_OF_MONTH)




        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                    view,year,month,dom->
                val sel_date  = "$dom/${month+1}/$year"
                binding.selDateTxt.setText("Selected date :  $sel_date")

                val birthday : LocalDate = LocalDate.of(year,(month+1),dom)
                val today : LocalDate = LocalDate.now()

                binding.resTxt.setText(calculateAge(birthday,today))


            },y,m,DoM).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateAge(birthday:LocalDate, today:LocalDate) :String{

        val age = Period.between(birthday, today)
        return "Age: " + age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days"
    }



}