package com.simplemobiletools.calculator.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.*
import com.simplemobiletools.calculator.BuildConfig
import com.simplemobiletools.calculator.R
import com.simplemobiletools.calculator.extensions.config
import com.simplemobiletools.commons.extensions.*
import com.simplemobiletools.commons.helpers.LICENSE_AUTOFITTEXTVIEW
import com.simplemobiletools.commons.helpers.LICENSE_ESPRESSO
import com.simplemobiletools.commons.helpers.LICENSE_ROBOLECTRIC
import com.simplemobiletools.commons.models.FAQItem
import kotlinx.android.synthetic.main.activity_unit.*
import android.widget.TextView



class UnitActivity : SimpleActivity() {
    var listOfUnit = arrayOf("Kilometer", "Meter", "Centimeter", "Millimeter", "Micrometer", "Nanometer", "Mile", "Yard", "Foot", "Inch")
    var spinner: Spinner? = null
    var textView_msg: TextView? = null
    var unit1: String = "Kilometer"
    var unit2: String = "Kilometer"

    private var storedTextColor = 0
    private var vibrateOnButtonPress = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit)
        appLaunched(BuildConfig.APPLICATION_ID)

        var intUnit1: Double = txtUnit1.text.toString().toDouble()
        var result: Double


        spinnerUnit1.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOfUnit)
        spinnerUnit1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                unit1 = listOfUnit[position]
                intUnit1 = txtUnit1.text.toString().toDouble()
                result = calculateLength(unit1, unit2, intUnit1)
                result = Math.round(result *10000.0)/10000.0
                Toast.makeText(this@UnitActivity, "Unit 1:" + listOfUnit[position], Toast.LENGTH_LONG).show()
                unitResult.setText(result.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spinnerUnit2.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOfUnit)
        spinnerUnit2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                unit2 = listOfUnit[position]
                intUnit1 = txtUnit1.text.toString().toDouble()
                result = calculateLength(unit1, unit2, intUnit1)
                result = Math.round(result *10000.0)/10000.0
                unitResult.setText(result.toString())
                Toast.makeText(this@UnitActivity, listOfUnit[position] + ":" + result.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        txtUnit1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @SuppressLint("ResourceType")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    intUnit1 = s.toString().toDouble()
                    result = calculateLength(unit1, unit2, intUnit1)
                    result = Math.round(result *10000.0)/10000.0
                    Toast.makeText(this@UnitActivity, unit1 + ":" + intUnit1.toString() + unit2 + ":" + result.toString(), Toast.LENGTH_LONG).show()
                }
                catch (e: NumberFormatException){
                    Toast.makeText(this@UnitActivity,"Enter Value", Toast.LENGTH_LONG).show()
                    result = 0.0
                }
                unitResult.setText(result.toString())
            }

        })
    }

    fun calculateLength(unit1: String, unit2: String, intUnit1: Double): Double =
            when (unit1) {
                "Kilometer" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 * 1
                        "Meter" -> intUnit1 * 1000
                        "Centimeter" -> intUnit1 * 100000
                        "Millimeter" -> intUnit1 * 1e+6
                        "Micrometer" -> intUnit1 * 1e+9
                        "Nanometer" -> intUnit1 * 1e+12
                        "Mile" -> intUnit1 / 1.609
                        "Yard" -> intUnit1 * 1093.613
                        "Foot" -> intUnit1 * 3280.84
                        else -> intUnit1 * 39370.079
                    }
                }
                "Meter" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 / 1000
                        "Meter" -> intUnit1 * 1
                        "Centimeter" -> intUnit1 * 100
                        "Millimeter" -> intUnit1 * 1000
                        "Micrometer" -> intUnit1 * 1e+6
                        "Nanometer" -> intUnit1 * 1e+9
                        "Mile" -> intUnit1 / 1609.344
                        "Yard" -> intUnit1 * 1.094
                        "Foot" -> intUnit1 * 3.281
                        else -> intUnit1 * 39.37
                    }
                }
                "Centimeter" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 / 100000
                        "Meter" -> intUnit1 / 100
                        "Centimeter" -> intUnit1
                        "Millimeter" -> intUnit1 * 10
                        "Micrometer" -> intUnit1 * 10000
                        "Nanometer" -> intUnit1 * 1e+7
                        "Mile" -> intUnit1 / 160934.4
                        "Yard" -> intUnit1 / 91.44
                        "Foot" -> intUnit1 / 3048
                        else -> intUnit1 / 2.54
                    }
                }
                "Millimeter" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 / 1000000
                        "Meter" -> intUnit1 / 1000
                        "Centimeter" -> intUnit1 / 10
                        "Millimeter" -> intUnit1
                        "Micrometer" -> intUnit1 * 1000
                        "Nanometer" -> intUnit1 * 1e+6
                        "Mile" -> intUnit1 / 1.609e+6
                        "Yard" -> intUnit1 / 914.4
                        "Foot" -> intUnit1 / 304.8
                        else -> intUnit1 / 25.4
                    }
                }
                "Micrometer" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 / 1e+9
                        "Meter" -> intUnit1 / 1e+6
                        "Centimeter" -> intUnit1 / 10000
                        "Millimeter" -> intUnit1 / 1000
                        "Micrometer" -> intUnit1
                        "Nanometer" -> intUnit1 * 1000
                        "Mile" -> intUnit1 / 1.609e+9
                        "Yard" -> intUnit1 / 914400
                        "Foot" -> intUnit1 / 304800
                        else -> intUnit1 / 25400
                    }
                }
                "Nanometer" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 / 1e+12
                        "Meter" -> intUnit1 / 1e+9
                        "Centimeter" -> intUnit1 / 1e+7
                        "Millimeter" -> intUnit1 / 1e+6
                        "Micrometer" -> intUnit1 / 1000
                        "Nanometer" -> intUnit1
                        "Mile" -> intUnit1 / 1.609e+12
                        "Yard" -> intUnit1 / 9.144e+8
                        "Foot" -> intUnit1 / 3.048e+8
                        else -> intUnit1 / 2.54e+7
                    }
                }
                "Mile" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 * 1.609
                        "Meter" -> intUnit1 * 1609.344
                        "Centimeter" -> intUnit1 * 160934.4
                        "Millimeter" -> intUnit1 * 1.609e+6
                        "Micrometer" -> intUnit1 * 1.609e+9
                        "Nanometer" -> intUnit1 * 1.609e+12
                        "Mile" -> intUnit1
                        "Yard" -> intUnit1 * 1760
                        "Foot" -> intUnit1 * 5280
                        else -> intUnit1 * 63360
                    }
                }
                "Yard" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 / 1093.613
                        "Meter" -> intUnit1 / 1.094
                        "Centimeter" -> intUnit1 * 91.44
                        "Millimeter" -> intUnit1 *  914.4
                        "Micrometer" -> intUnit1 * 914400
                        "Nanometer" -> intUnit1 * 9.144e+8
                        "Mile" -> intUnit1 / 1760
                        "Yard" -> intUnit1
                        "Foot" -> intUnit1 * 3
                        else -> intUnit1 * 36
                    }
                }
                "Foot" -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 / 3280.84
                        "Meter" -> intUnit1 / 3.281
                        "Centimeter" -> intUnit1 * 30.48
                        "Millimeter" -> intUnit1 * 304.8
                        "Micrometer" -> intUnit1 * 304800
                        "Nanometer" -> intUnit1 * 3.048e+8
                        "Mile" -> intUnit1 / 5280
                        "Yard" -> intUnit1 * 3
                        "Foot" -> intUnit1
                        else -> intUnit1 * 12
                    }
                }
                else -> {
                    when (unit2) {
                        "Kilometer" -> intUnit1 / 39370.079
                        "Meter" -> intUnit1 / 39.37
                        "Centimeter" -> intUnit1 * 2.54
                        "Millimeter" -> intUnit1 * 25.4
                        "Micrometer" -> intUnit1 * 25400
                        "Nanometer" -> intUnit1 * 2.54e+7
                        "Mile" -> intUnit1 / 63360
                        "Yard" -> intUnit1 / 36
                        "Foot" -> intUnit1 /12
                        else -> intUnit1
                    }
                }
            }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> launchSettings()
            R.id.about -> launchAbout()
            R.id.calculator -> launchCalculator()
            R.id.unit -> launchUnit()
            R.id.discountCalculator -> launchDiscount()
            R.id.taxCalculator -> launchTax()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun storeStateVariables() {
        config.apply {
            storedTextColor = textColor
        }
    }

    private fun checkHaptic(view: View) {
        if (vibrateOnButtonPress) {
            view.performHapticFeedback()
        }
    }

    private fun launchCalculator() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    private fun launchUnit() {
        startActivity(Intent(applicationContext, UnitActivity::class.java))
    }

    private fun launchDiscount() {
        startActivity(Intent(applicationContext, DiscountActivity::class.java))
    }

    private fun launchTax() {
        startActivity(Intent(applicationContext, TaxActivity::class.java))
    }

    private fun launchSettings() {
        startActivity(Intent(applicationContext, SettingsActivity::class.java))
    }

    private fun launchAbout() {
        val licenses = LICENSE_AUTOFITTEXTVIEW or LICENSE_ROBOLECTRIC or LICENSE_ESPRESSO

        val faqItems = arrayListOf(
                FAQItem(R.string.faq_1_title, R.string.faq_1_text),
                FAQItem(R.string.faq_1_title_commons, R.string.faq_1_text_commons),
                FAQItem(R.string.faq_4_title_commons, R.string.faq_4_text_commons),
                FAQItem(R.string.faq_2_title_commons, R.string.faq_2_text_commons)
        )

        startAboutActivity(R.string.app_name, licenses, BuildConfig.VERSION_NAME, faqItems, true)
    }
}
