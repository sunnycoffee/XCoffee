package me.coffee.utils

import android.text.Editable
import android.text.TextWatcher

class PhoneFormatter : TextWatcher {

    private val phoneReg = "(\\d{0,3})(\\d{0,4})(\\d{0,4})"
    private val otherReg = "\\D"
    private var lastValue: String? = null

    override fun afterTextChanged(s: Editable?) {
        var value = s.toString()
        if (value != this.lastValue) {
            value = value
                .replace(otherReg.toRegex(), "")
                .replace(phoneReg.toRegex(), "$1 $2 $3")
                .trim()
            this.lastValue = value
            s?.replace(0, s.length, value)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


}