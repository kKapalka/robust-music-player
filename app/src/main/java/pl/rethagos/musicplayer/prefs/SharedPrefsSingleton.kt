package pl.rethagos.musicplayer.prefs

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPrefsSingleton private constructor() {
    private var sharedPref: SharedPreferences? = null

    fun init(activity: Activity) {
        sharedPref = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    var isInitialLaunch: Boolean?
        get() = sharedPref!!.getBoolean("initialLaunch", true)
        set(initialLaunch) {
            sharedPref!!.edit().putBoolean("initialLaunch", initialLaunch!!).apply()
        }

    companion object {
        var instance: SharedPrefsSingleton? = null
            get() {
                if (field == null) {
                    field = SharedPrefsSingleton()
                }
                return field
            }
            private set
    }
}