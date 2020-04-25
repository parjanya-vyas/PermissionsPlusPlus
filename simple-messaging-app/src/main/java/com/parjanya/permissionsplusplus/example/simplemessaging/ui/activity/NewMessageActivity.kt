package com.parjanya.permissionsplusplus.example.simplemessaging.ui.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.parjanya.permissionsplusplus.R
import com.parjanya.permissionsplusplus.example.simplemessaging.data.Message
import kotlinx.android.synthetic.main.activity_new_message.*
import java.text.SimpleDateFormat
import java.util.*

class NewMessageActivity : AppCompatActivity(R.layout.activity_new_message) {

    companion object {
        const val MESSAGE_EXTRA = "MESSAGE_EXTRA"
        const val DATE_FORMAT = "dd MMMM, yyyy"
        const val TIME_FORMAT = "hh:mm"
    }

    private val onDateSelectedListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        val selectedDate = Calendar.getInstance()
        selectedDate.set(year, month, dayOfMonth)
        updateDateLabel(selectedDate)
    }

    private val onTimeSelectedListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        val selectedTime = Calendar.getInstance()
        selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
        selectedTime.set(Calendar.MINUTE, minute)
        updateTimeLabel(selectedTime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAllInputFields()
        setupOnClickListeners()
    }

    private fun updateDateLabel(selectedDate: Calendar) {
        dateEditText.setText(SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(selectedDate.time))
    }

    private fun updateTimeLabel(selectedTime: Calendar) {
        timeEditText.setText(SimpleDateFormat(TIME_FORMAT, Locale.getDefault()).format(selectedTime.time))
    }

    private fun setupOnClickListeners() {
        val currentDate = Calendar.getInstance()
        dateEditText.setOnClickListener {
            DatePickerDialog(this,
                onDateSelectedListener,
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH))
                .show()
        }
        timeEditText.setOnClickListener {
            TimePickerDialog(this,
                onTimeSelectedListener,
                currentDate.get(Calendar.HOUR_OF_DAY),
                currentDate.get(Calendar.MINUTE),
                true)
                .show()
        }
        submitButton.setOnClickListener {
            if (validateAllInputs()) {
                val senderInput = senderEditText.text.toString()
                val dateTimeInput = SimpleDateFormat("$DATE_FORMAT $TIME_FORMAT", Locale.getDefault()).parse("${dateEditText.text.toString()} ${timeEditText.text.toString()}")!!
                val bodyInput = bodyEditText.text.toString()
                val replyIntent = Intent()
                replyIntent.putExtra(MESSAGE_EXTRA, Message(
                    sender = senderInput,
                    date = dateTimeInput.time,
                    body = bodyInput))
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }

    private fun validateAllInputs(): Boolean {
        return validate(senderTil, senderEditText)
                && validate(dateTil, dateEditText)
                && validate(timeTil, timeEditText)
                && validate(bodyTil, bodyEditText)
    }

    private fun setupAllInputFields() {
        setupInputField(senderTil, senderEditText)
        setupInputField(dateTil, dateEditText)
        setupInputField(timeTil, timeEditText)
        setupInputField(bodyTil, bodyEditText)
    }

    private fun setupInputField(textInputLayout: TextInputLayout, textInputEditText: TextInputEditText) {
        textInputEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                textInputLayout.hideEmptyError()
            else
                validate(textInputLayout, textInputEditText)
        }
    }

    private fun validate(textInputLayout: TextInputLayout, textInputEditText: TextInputEditText): Boolean {
        return if (textInputEditText.text.toString().isBlank()) {
            textInputLayout.showEmptyError()
            false
        } else {
            true
        }
    }

    private fun TextInputLayout.hideEmptyError() {
        error = ""
    }

    private fun TextInputLayout.showEmptyError() {
        error = "Cannot leave this field blank"
    }

}