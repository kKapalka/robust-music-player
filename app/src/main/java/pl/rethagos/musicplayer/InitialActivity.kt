package pl.rethagos.musicplayer

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import pl.rethagos.musicplayer.model.AudioFile
import pl.rethagos.musicplayer.model.FolderPath
import pl.rethagos.musicplayer.prefs.SharedPrefsSingleton
import java.io.File
import java.util.stream.Collectors
import kotlin.system.exitProcess


class InitialActivity : AppCompatActivity() {

    private lateinit var descText: TextView
    private lateinit var textView: TextView
    private lateinit var helloLayout: ConstraintLayout
    private lateinit var toolbarNavi: Toolbar
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
        toolbarNavi = findViewById(R.id.toolbar_navi)
        setSupportActionBar(toolbarNavi)
        textView.text = listFoldersFrom(null).toString()
    }

    // Menu icons are inflated just as they were with actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_navi, menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return true
    }

    fun listFoldersFrom(path: String?): List<FolderPath> {
        var folderPathList: ArrayList<FolderPath> = ArrayList()
        var relativePathList: ArrayList<String> = ArrayList()
        if(path == null) {
            println("path = null")
            var c: Cursor? = contentResolver?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.Audio.AudioColumns.BUCKET_DISPLAY_NAME, MediaStore.Audio.AudioColumns.RELATIVE_PATH), null, null, null)
            var relPath: String? = ""
            var name: String? = ""
            if(c != null) {
                while(c.moveToNext()) {
                    relPath = c.getString(1)
                    if(relPath != null) {
                        if (relPath in relativePathList) {
                        } else {
                            relativePathList.add(relPath)
                            name = c.getString(0)
                            folderPathList.add(FolderPath(c.getString(0), relPath.replace("/$name", "")))
                        }
                    }
                }
            }
            c = contentResolver?.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, arrayOf(MediaStore.Audio.AudioColumns.BUCKET_DISPLAY_NAME, MediaStore.Audio.AudioColumns.RELATIVE_PATH), null, null, null)
            if(c != null) {
                while(c.moveToNext()) {
                    relPath = c.getString(1)
                    if(relPath != null) {
                        if (relPath in relativePathList) {
                        } else {
                            relativePathList.add(relPath)
                            name = c.getString(0)
                            folderPathList.add(FolderPath(c.getString(0), relPath.replace("/$name", "")))
                        }
                    }
                }
            }
        }
        return folderPathList
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