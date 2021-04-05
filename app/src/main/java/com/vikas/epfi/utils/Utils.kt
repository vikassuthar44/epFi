package com.vikas.epfi.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object Utils {



    fun validatePanNumber(panNumber: String) : Boolean {
        val mPattern: Pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")

        val mMatcher: Matcher = mPattern.matcher(panNumber)
        return mMatcher.matches()
    }

}