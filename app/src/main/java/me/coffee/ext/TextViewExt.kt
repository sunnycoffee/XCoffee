package me.coffee.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

/**
 *TextView的拓展
 *
 * @author kongfei
 */

fun TextView.addAfterTextChangedListener(listener: (Editable) -> Unit) {

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            s?.let { listener(it) }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    })
}