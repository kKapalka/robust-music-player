package pl.rethagos.musicplayer.activity

import android.media.AsyncPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import pl.rethagos.musicplayer.R
import pl.rethagos.musicplayer.model.AudioFile
import pl.rethagos.musicplayer.model.FolderPath
import pl.rethagos.musicplayer.recyclerview.FolderPathAdapter
import pl.rethagos.musicplayer.recyclerview.OnFolderPathClickListener

class MusicActivity : AppCompatActivity() {

    private lateinit var toolbarNavi: Toolbar

    private lateinit var songName: TextView
    private lateinit var songAuthor: TextView
    private lateinit var songAlbum: TextView
    private lateinit var songStart: TextView
    private lateinit var songEnd: TextView


    private var currentTitle: String? = null
    private var currentPath: String? = null
    private var currentFolderFiles: ArrayList<AudioFile>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        songName = findViewById(R.id.songName)
        songAuthor = findViewById(R.id.songAuthor)
        songAlbum = findViewById(R.id.songAlbum)
        songStart = findViewById(R.id.songStart)
        songEnd = findViewById(R.id.songEnd)
        currentTitle = intent.extras?.getString("currentTitle")
        currentPath = intent.extras?.getString("currentPath")
        currentFolderFiles = intent.extras?.getSerializable("currentFolderFiles") as ArrayList<AudioFile>?

        println(currentTitle)
        println(currentPath)
        println(currentFolderFiles)

        toolbarNavi = findViewById(R.id.toolbar_navi_music)
        setSupportActionBar(toolbarNavi)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        loadCurrentSong()

    }

    private fun loadCurrentSong() {
        songName.text = currentTitle
    }

    // Menu icons are inflated just as they were with actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_navi, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.menu_settings -> {
                println("settings pressed")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}