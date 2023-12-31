package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.flStart?.setOnClickListener(){
            val intent=Intent(this,ExerciseActivity::class.java)
            startActivity(intent)

           // Toast.makeText(this,"Lets Start the exercise",Toast.LENGTH_LONG).show()
        }

        binding?.flBMI?.setOnClickListener(){
            val intent=Intent(this,BMIActivity::class.java)
            startActivity(intent)

            // Toast.makeText(this,"Lets Start the exercise",Toast.LENGTH_LONG).show()
        }

        binding?.flHistory?.setOnClickListener(){
            val intent=Intent(this,HistoryActivit::class.java)
            startActivity(intent)

            // Toast.makeText(this,"Lets Start the exercise",Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

}