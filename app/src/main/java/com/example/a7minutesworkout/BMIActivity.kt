package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNIT_VIEW="METRIC_UNIT_VIEW"
        private const val US_UNIT_VIEW="US_UNIT_VIEW"
    }
    private var currentVisibleView:String= METRIC_UNIT_VIEW
    private var binding:ActivityBmiBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmi)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="BMI Calculator"
        }
        binding?.toolbarBmi?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.btnBMICalculator?.setOnClickListener{
            if(validateMetricUnits()){
                val heightValue:Float=binding?.etMetricUniHeight?.text.toString().toFloat()/100
                val weightValue:Float=binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi=weightValue/(heightValue*heightValue)
                displayBMiResult(bmi)
            }
            else{
                Toast.makeText(this,"Enter Height and Weight First",Toast.LENGTH_LONG).show()
            }
        }

        //binding?.etMetricUnitWeight?.text=
    }
    private fun makeVisibleMetricUnitview(){

    }

    private fun displayBMiResult(bmi:Float){
        var bmiDescription:String
        var bmiLabel:String

        if(bmi.compareTo(15f)<=0){
            bmiLabel="You are very severely UnderWeight"
            bmiDescription="Oh you need to take care of yourself,try to eat something"
        }
        else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0) {
            bmiLabel = "You are severely UnderWeight"
            bmiDescription = "Oh you need to take care of yourself, try to eat something"
        }
        else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f)<=0) {
            bmiLabel = "You are UnderWeight"
            bmiDescription = "Oh you need to take care of yourself, try to eat something"
        }
        else if(bmi.compareTo(18.5f)>0 && bmi.compareTo(25f)<=0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape"
        }
        else if(bmi.compareTo(25f)>0 && bmi.compareTo(30f)<=0) {
            bmiLabel = "OverWeight"
            bmiDescription = "Oh you need to take care of yourself, try to workout and dieting"
        }
        else if(bmi.compareTo(30f)>0 && bmi.compareTo(35f)<=0) {
            bmiLabel = "Very Overweight"
            bmiDescription = "Oh you need to take care of yourself, try to workout and dieting"
        }
        else if(bmi.compareTo(35f)>0 && bmi.compareTo(40f)<=0) {
            bmiLabel = "Too Much Overweight"
            bmiDescription = "Oh you need to take care of yourself, try to workout and dieting"
        }
        else{
            bmiLabel="ExtraOrdinary"
            bmiDescription="You are Under Serious Condition, try to workout and dieting"
        }
        val bmiValue=BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.llDisplayBMIResult?.visibility=View.VISIBLE
        binding?.tvBMIValue?.text=bmiValue
        binding?.tvBMIType?.text=bmiLabel
        binding?.tvBMIDescription?.text=bmiDescription
    }
    fun validateMetricUnits():Boolean {
        var validate = true

        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()) {
            validate = false
        } else if (binding?.etMetricUniHeight?.text.toString().isEmpty()) {
            validate = false
        }
        return validate
    }
}