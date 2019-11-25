package me.coffee.ext

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.util.*

/**
 *activity扩展
 *
 * @author kongfei
 */

fun Activity.autoHideKeyboard(event: MotionEvent?): Boolean {
    if (event?.action != MotionEvent.ACTION_DOWN) return false
    val window = this.window ?: return false
    val root = window.decorView as ViewGroup
    val list = getAllEditTextView(root)
    return hideKeyboard(this, event, list)
}

private fun getAllEditTextView(vg: ViewGroup?): List<EditText>? {
    if (vg == null || vg.childCount == 0) return null
    val result = ArrayList<EditText>()
    for (i in 0 until vg.childCount) {
        val child = vg.getChildAt(i)
        if (child is EditText) {
            result.add(child)
        } else if (child is ViewGroup) {
            val children = getAllEditTextView(child)
            if (children != null) result.addAll(children)
        }
    }
    return result
}

private fun hideKeyboard(activity: Activity, event: MotionEvent, views: List<View>?): Boolean {
    if (views == null || views.isEmpty()) return false
    var result = false
    var flag = false
    val viewRect = Rect()
    for (v in views) {
        if (!v.isShown) continue
        viewRect.setEmpty()
        v.getGlobalVisibleRect(viewRect)
        if (viewRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
            flag = true
            break
        } else {
            v.clearFocus()
            v.parent?.let {
                it as View
                it.requestFocus()
            }
        }
    }
    if (!flag) {
        val v = views[0]
        val token = v.windowToken
        val manager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(token, 0)
        result = true

    }
    return result
}