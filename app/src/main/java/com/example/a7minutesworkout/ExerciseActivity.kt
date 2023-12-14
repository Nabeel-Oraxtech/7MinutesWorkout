package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding:ActivityExerciseBinding?=null
    private var restTimer: CountDownTimer?=null
    private var restProgress=0

    private var restExerciseTimer:CountDownTimer?=null
    private var restExerciseProgressbar=0

    var exerciseList:ArrayList<ExerciseModel>?=null
    var currentExercisePosition=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.exerciseTB)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.exerciseTB?.setNavigationOnClickListener(){
            onBackPressed()
        }
        setRestView()

        exerciseList=Constants.defaultExerciseList()
    }
    private fun setRestView(){
        binding?.FLtoolbar?.visibility=View.VISIBLE
        binding?.TVtoolbar?.visibility=View.VISIBLE
        binding?.FLExercisetoolbar?.visibility=View.INVISIBLE
        binding?.tvExercise?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        setRestProgressbar()
    }
    private  fun setRestExerciseView(){
        binding?.FLtoolbar?.visibility=View.INVISIBLE
        binding?.TVtoolbar?.visibility=View.INVISIBLE
        binding?.FLExercisetoolbar?.visibility=View.VISIBLE
        binding?.tvExercise?.visibility=View.VISIBLE
        binding?.ivImage?.visibility=View.VISIBLE

        if(restExerciseTimer!=null){
            restExerciseTimer?.cancel()
            restExerciseProgressbar=0
        }
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExercise?.text=exerciseList!![currentExercisePosition].getName()
        setExerciseProgressbar()
    }
    private fun setExerciseProgressbar(){
        binding?.ExerciseprogressBAr?.progress=restExerciseProgressbar
        restExerciseTimer= object : CountDownTimer(30000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                restExerciseProgressbar++
                binding?.ExerciseprogressBAr?.progress = 30 - restExerciseProgressbar
                binding?.TvExerciseTimer?.text=(30-restExerciseProgressbar).toString()

            }

            override fun onFinish() {
              if(currentExercisePosition<=exerciseList?.size !! -1){
                  setRestView()
              }
                else{
                    Toast.makeText(this@ExerciseActivity,"Exercises Completed",Toast.LENGTH_LONG).show()
                }
            }

        }.start()
    }
    private fun setRestProgressbar(){
        binding?.progressBAr?.progress=restProgress
        restTimer= object : CountDownTimer(10000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBAr?.progress = 10 - restProgress
                binding?.TvTimer?.text=(10-restProgress).toString()
            }


            override fun onFinish() {
                currentExercisePosition++
               setRestExerciseView()

            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        if(restExerciseTimer!=null){
            restExerciseTimer?.cancel()
            restExerciseProgressbar=0
        }
        binding=null
    }
}


