package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding

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
            onBackPressed()        }
    }
}