package pl.rethagos.musicplayer.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import pl.rethagos.musicplayer.R

class MusicActivity : AppCompatActivity() {

    private lateinit var toolbarNavi: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        toolbarNavi = findViewById(R.id.toolbar_navi_music)
        setSupportActionBar(toolbarNavi)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
    // Menu icons are inflated just as they were with actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_navi, menu)
        return true
    }
}