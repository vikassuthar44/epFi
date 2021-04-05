package com.vikas.epfi.ui.login

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.epfi.common.Constants
import com.vikas.epfi.utils.Utils
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class LoginViewModel @Inject constructor(
) : ViewModel() {

    private val _validPan = MutableLiveData<Boolean>()
    val routeEventPan: LiveData<Boolean> = _validPan

    private val _birthDay = MutableLiveData<String>()
    val routeEventDay: LiveData<String> = _birthDay

    private val _birthMonth = MutableLiveData<String>()
    val routeEventMonth: LiveData<String> = _birthMonth

    private val _birthYear = MutableLiveData<String>()
    val routeEventYear: LiveData<String> = _birthYear

    lateinit var panNumber: String

    private val _time = MutableLiveData<Boolean>()
    val routeEvent: LiveData<Boolean> = _time


    fun initFinishActivity() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(Constants.FINSIH_ACTIVITY)
            postRouteEventFinsh(true)
        }
    }

    private fun postRouteEventFinsh(isTime: Boolean) {
        _time.postValue(isTime)
    }

    fun validePanNumber() {
        viewModelScope.launch {
            if (Utils.validatePanNumber(panNumber))
                postRouteEvent(true)
            else
                postRouteEvent(false)
        }
    }

    private fun postRouteEvent(isValid: Boolean) {
        _validPan.postValue(isValid)
    }


    fun selectBirthDate(context: Context) {
        viewModelScope.launch {
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            val pickerListener = OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                var day = selectedDay.toString()
                if (day.length == 1) {
                    day = "0$selectedDay"
                }
                postRouteEventDay(day)

                var selectMonth = selectedMonth.toInt() + 1
                var month = selectMonth.toString()
                if (month.length == 1) {
                    month = "0$selectMonth"
                }
                postRouteEventMonth(month)

                postRouteEventYear(selectedYear.toString())

            }


            val dpDialog = DatePickerDialog(context, pickerListener, year, month, day)
            val datePicker = dpDialog.datePicker
            datePicker.maxDate = System.currentTimeMillis()
            dpDialog.show()



        }
    }

    private fun postRouteEventDay(day: String) {
        _birthDay.postValue(day)
    }


    private fun postRouteEventMonth(month: String) {
        _birthMonth.postValue(month)
    }


    private fun postRouteEventYear(year: String) {
        _birthYear.postValue(year)
    }


}