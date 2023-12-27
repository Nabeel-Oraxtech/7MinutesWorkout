package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.room.Insert
import com.example.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding:ActivityFinishBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val historyDao = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(historyDao)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarFinish)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinish?.setNavigationOnClickListener(){
            onBackPressed()
        }

       binding?.btnFinish?.setOnClickListener(){
           finish()
        }
    }
    private fun addDateToDatabase(historyDao: HistoryDao){
        val c=Calendar.getInstance()
        val dateTime=c.time
        Log.e("Date","" + dateTime)

        val sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date=sdf.format(dateTime)
        Log.e("Formatted Date","" + date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e("Date","Added" )

        }
    }


}