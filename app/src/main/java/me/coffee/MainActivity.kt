package me.coffee

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import me.coffee.ext.autoHideKeyboard
import me.coffee.utils.XUtil

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, XUtil.context.getString(R.string.app_name))
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        autoHideKeyboard(ev)
        return super.dispatchTouchEvent(ev)
    }
}
