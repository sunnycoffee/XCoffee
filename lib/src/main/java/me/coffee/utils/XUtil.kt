package me.coffee.utils

import android.annotation.SuppressLint
import android.app.Application

/**
 * 通用工具类
 *
 * @author kongfei
 */
object XUtil {

    val context by lazy {
        @SuppressLint("PrivateApi")
        val activityThread = Class.forName("android.app.ActivityThread")
        val thread = activityThread.getMethod("currentActivityThread").invoke(null as Any?)
        val app = activityThread.getMethod("getApplication").invoke(thread)
        app as Application
    }

}