package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivit : AppCompatActivity() {

    private var binding:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        setSupportActionBar(binding?.historyTB)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="History"
        }
        binding?.historyTB?.setNavigationOnClickListener(){
            onBackPressed()
        }
        val historyDao = (application as WorkOutApp).db.historyDao()
        getAllDatesData(historyDao)
    }
    private fun getAllDatesData(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{ allDatesList ->
                for (i in allDatesList){
                    Log.e("date","" +i.date)
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}