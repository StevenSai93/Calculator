package com.simplemobiletools.calculator.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.simplemobiletools.calculator.BuildConfig
import com.simplemobiletools.calculator.R
import com.simplemobiletools.commons.extensions.appLaunched
import com.simplemobiletools.commons.helpers.LICENSE_AUTOFITTEXTVIEW
import com.simplemobiletools.commons.helpers.LICENSE_ESPRESSO
import com.simplemobiletools.commons.helpers.LICENSE_ROBOLECTRIC
import com.simplemobiletools.commons.models.FAQItem
import kotlinx.android.synthetic.main.activity_tax.*

class TaxActivity : SimpleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax)
        appLaunched(BuildConfig.APPLICATION_ID)

        btn_tax.setOnClickListener{
            val price: Double = taxPrice.text.toString().toDouble()
            val tax: Double = taxPercentage.text.toString().toDouble()
            val result = price + (price * (tax/100))
            taxResult.setText(result.toString())
            Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show()
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

    private fun launchCalculator(){
        startActivity(Intent(applicationContext, MainActivity:: class.java))
    }

    private fun launchUnit() {
        startActivity(Intent(applicationContext, UnitActivity::class.java))
    }
    private fun launchDiscount(){
        startActivity(Intent(applicationContext, DiscountActivity:: class.java))
    }

    private fun launchTax(){
        startActivity(Intent(applicationContext, TaxActivity:: class.java))
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