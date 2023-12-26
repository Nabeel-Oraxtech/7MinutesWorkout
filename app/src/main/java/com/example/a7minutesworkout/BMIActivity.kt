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
        private const val METRIC_UNITS_VIEW="METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW="US_UNIT_VIEW"
    }
    private var currentVisibleView:String= METRIC_UNITS_VIEW
    private var binding:ActivityBmiBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmi)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="BMI Calculator"

        binding?.toolbarBmi?.setNavigationOnClickListener{
            onBackPressed()
        }

        makeVisibleMetricUnitView()

        binding?.rgUnits?.setOnCheckedChangeListener{_,checkedID:Int ->

            if(checkedID==R.id.rbMetricUnits){
                makeVisibleMetricUnitView()
            }
            else{
                makeVisibleUSUnitView()
            }
        }

        binding?.btnBMICalculator?.setOnClickListener{
            calculateUnits()
        }

        //binding?.etMetricUnitWeight?.text=
    }
    private fun calculateUnits(){
        if(currentVisibleView== METRIC_UNITS_VIEW){
            if(validateMetricUnits()){
                val heightValue:Float=binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightValue:Float=binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi=weightValue/(heightValue*heightValue)
                displayBMiResult(bmi)
            }

            else{
                Toast.makeText(this,"Enter Height and Weight First",Toast.LENGTH_LONG).show()
            }
        }
        else{
            if(validateUSUnits()){
                val USUnitsheightValueFeet:String=binding?.etUSUniHeightFeet?.text.toString()
                val USUnitsheightValueInch:String=binding?.etUSUniHeightInch?.text.toString()
                val USUnitsweightValue:Float=binding?.etUSUnitWeight?.text.toString().toFloat()

                val heightValue=USUnitsheightValueInch.toFloat() + USUnitsheightValueFeet.toFloat() * 12
                val bmi=703*(USUnitsweightValue/(heightValue*heightValue))

                displayBMiResult(bmi)
            }
            else{
                Toast.makeText(this,"Enter Height and Weight First",Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun makeVisibleMetricUnitView(){
        currentVisibleView= METRIC_UNITS_VIEW
        binding?.titleMetricUnitWeight?.visibility=View.VISIBLE
        binding?.titleMetricUnitHeight?.visibility=View.VISIBLE
        binding?.titleUSUnitHeightInch?.visibility=View.GONE
        binding?.titleUSUnitHeightFeet?.visibility=View.GONE
        binding?.titleUsUnitWeight?.visibility = View.GONE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility=View.INVISIBLE
    }

    private fun makeVisibleUSUnitView(){
        currentVisibleView= US_UNITS_VIEW
        binding?.titleMetricUnitWeight?.visibility=View.INVISIBLE
        binding?.titleMetricUnitHeight?.visibility=View.INVISIBLE
        binding?.titleUSUnitHeightInch?.visibility=View.VISIBLE
        binding?.titleUSUnitHeightFeet?.visibility=View.VISIBLE
        binding?.titleUsUnitWeight?.visibility = View.VISIBLE

        binding?.etUSUniHeightInch?.text!!.clear()
        binding?.etUSUniHeightFeet?.text!!.clear()
        binding?.etUSUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility=View.INVISIBLE
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
        } else if (binding?.etMetricUnitHeight?.text.toString().isEmpty()) {
            validate = false
        }
        return validate
    }

fun validateUSUnits():Boolean {
    var validate = true

    if (binding?.etUSUnitWeight?.text.toString().isEmpty()) {
        validate = false
    } else if (binding?.etUSUniHeightFeet?.text.toString().isEmpty()) {
        validate = false
    }
    else if (binding?.etUSUniHeightInch?.text.toString().isEmpty()) {
        validate = false
    }
    return validate
}
}