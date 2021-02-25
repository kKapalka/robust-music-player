package pl.rethagos.musicplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import pl.rethagos.musicplayer.prefs.SharedPrefsSingleton
import kotlin.system.exitProcess


class InitialActivity : AppCompatActivity() {

    private lateinit var descText: TextView
    private lateinit var textView: TextView
    private lateinit var helloLayout: ConstraintLayout
    private var readPermissionGranted: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readPermissionGranted = ActivityCompat.checkSelfPermission(this@InitialActivity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        SharedPrefsSingleton.instance?.init(this)
        if(!SharedPrefsSingleton.instance?.isInitialLaunch!! && readPermissionGranted) {
            handleStandardLayoutDisplay()
        } else {
            handleInitialLayoutDisplay()
        }

    }

    private fun handleStandardLayoutDisplay() {
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        textView.text = "Sup my dude"
    }

    private fun handleInitialLayoutDisplay() {
        setContentView(R.layout.activity_initial)
        descText = findViewById(R.id.hello_description_textview)
        helloLayout = findViewById(R.id.hello_constraint_layout)
        if (readPermissionGranted) {
            descText.text = "Thank you for using this app. Please support the creator by giving it 5 stars in Google Play App Store"
        }
        helloLayout.setOnClickListener {
            if (!readPermissionGranted) {
                ActivityCompat.requestPermissions(this@InitialActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 123)
            } else {
                handleStandardLayoutDisplay()
            }
        }
        SharedPrefsSingleton.instance?.isInitialLaunch = false
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            123 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    handleStandardLayoutDisplay()
                } else {
                    this@InitialActivity.finish()
                    exitProcess(0)
                }
            }
        }
    }
}