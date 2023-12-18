package com.example.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener{
    private var binding:ActivityExerciseBinding?=null
    private var restTimer: CountDownTimer?=null
    private var restProgress=0

    private var restExerciseTimer:CountDownTimer?=null
    private var restExerciseProgressbar=0

    var exerciseList:ArrayList<ExerciseModel>?=null
    var currentExercisePosition=-1

    private var textToSpeech: TextToSpeech?=null
    private var player:MediaPlayer?=null

    private var exerciseAdapter:ExerciseStatusAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        textToSpeech=TextToSpeech(this,this)

        setSupportActionBar(binding?.exerciseTB)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.exerciseTB?.setNavigationOnClickListener(){
            onBackPressed()
        }
        setRestView()


        exerciseList=Constants.defaultExerciseList()
        setupExerciseStatusRecyclerView()

    }
    fun setupExerciseStatusRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter=ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter=exerciseAdapter
    }
    private fun setRestView(){
        try {
            val soundURI=Uri.parse(
                "android.resource://com.example.a7minutesworkout/" + R.raw.press_start)
            player=MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping=false
            player?.start()
        }
        catch (e:Exception){
            e.printStackTrace()
        }

        binding?.FLtoolbar?.visibility=View.VISIBLE
        binding?.TVtoolbar?.visibility=View.VISIBLE
        binding?.FLExercisetoolbar?.visibility=View.INVISIBLE
        binding?.tvExercise?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE

        binding?.tvUpcoming?.visibility=View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility=View.VISIBLE



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

        binding?.tvUpcoming?.visibility=View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility=View.INVISIBLE

        if(restExerciseTimer!=null){
            restExerciseTimer?.cancel()
            restExerciseProgressbar=0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExercise?.text=exerciseList!![currentExercisePosition].getName()


        setExerciseProgressbar()
    }
    private fun setExerciseProgressbar(){

        binding?.ExerciseprogressBAr?.progress=restExerciseProgressbar
        restExerciseTimer= object : CountDownTimer(3000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                restExerciseProgressbar++
                binding?.ExerciseprogressBAr?.progress = 30 - restExerciseProgressbar
                binding?.TvExerciseTimer?.text=(30-restExerciseProgressbar).toString()

            }

            override fun onFinish() {



              if(currentExercisePosition<exerciseList?.size !! -1){
                  exerciseList!![currentExercisePosition].setIsSelected(false)
                  exerciseList!![currentExercisePosition].setIsCompleted(true)
                  exerciseAdapter!!.notifyDataSetChanged()
                  setRestView()
              }
                else{
                    finish()
                  val intent=Intent(this@ExerciseActivity,FinishActivity::class.java)
                  startActivity(intent)
                }
            }

        }.start()
    }
    private fun setRestProgressbar(){
        binding?.progressBAr?.progress=restProgress
        restTimer= object : CountDownTimer(1000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBAr?.progress = 10 - restProgress
                binding?.TvTimer?.text=(10-restProgress).toString()
                binding?.tvUpcomingExerciseName?.text=exerciseList!![currentExercisePosition +1].getName()


            }



            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
               setRestExerciseView()

            }

        }.start()
    }
    private fun speakOut(text : String){
        textToSpeech?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
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
        if(textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        if(player!=null){
            player!!.stop()
        }
        binding=null
    }
    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result=textToSpeech?.setLanguage(Locale.UK)
            if(result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language is not Supported")
            }
            else{
                Log.e("TTS","Cant Initialized")
            }
        }
    }



}


