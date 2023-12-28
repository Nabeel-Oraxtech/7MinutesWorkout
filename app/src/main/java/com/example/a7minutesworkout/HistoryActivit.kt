package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
              if(allDatesList.isNotEmpty()){
                  binding?.rvHistory?.visibility=View.VISIBLE
                  binding?.tvHistory?.visibility=View.VISIBLE
                  binding?.noRecordHistory?.visibility=View.INVISIBLE

                  binding?.rvHistory?.layoutManager=LinearLayoutManager(this@HistoryActivit)

                  val dates=ArrayList<String>()
                  for(date in allDatesList){
                      dates.add(date.date)
                  }
                  val historyAdapter=HistoryAdapter(dates)
                  binding?.rvHistory?.adapter=historyAdapter
              }
                else{
                  binding?.rvHistory?.visibility=View.GONE
                  binding?.tvHistory?.visibility=View.GONE
                  binding?.noRecordHistory?.visibility=View.VISIBLE
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}