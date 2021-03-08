package pl.rethagos.musicplayer.activity

import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import pl.rethagos.musicplayer.R
import pl.rethagos.musicplayer.model.AudioFile


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
    private var currentAudioFile: AudioFile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        songName = findViewById(R.id.songName)
        songAuthor = findViewById(R.id.songAuthor)
        songAlbum = findViewById(R.id.songAlbum)
        songStart = findViewById(R.id.songStart)
        songEnd = findViewById(R.id.songEnd)
        currentTitle = intent.getStringExtra("currentTitle")
        currentPath = intent.getStringExtra("currentPath")
        currentFolderFiles = intent.getSerializableExtra("currentFolderFiles") as ArrayList<AudioFile>?
        currentAudioFile = currentFolderFiles?.find { it.title == currentTitle }
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
        songEnd.text = parseAsTime(currentAudioFile?.duration)
    }

    private fun parseAsTime(duration: String?): String{
        var durationAsMillis: Int = if(duration.isNullOrBlank()){
            val uri: Uri = Uri.parse(currentPath)
            val mmr = MediaMetadataRetriever()
            mmr.setDataSource(applicationContext, uri)
            val durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            durationStr!!.toInt()
        } else {
            duration.toInt()
        }
        val durAsInt = durationAsMillis / 1000
        var seconds = (durAsInt % 60).toString()
        var minutes = ((durAsInt / 60) % 60).toString()
        var hours = ((durAsInt / 3600) % 24).toString()
        hours = if(hours != "0" && hours.length == 1) {
            "0$hours:"
        } else {
            ""
        }
        if(minutes != "0" && minutes.length == 1) {
            minutes = "0$minutes"
        }
        if(seconds != "0" && seconds.length == 1) {
            seconds = "0$seconds"
        }
        return "$hours$minutes:$seconds"
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