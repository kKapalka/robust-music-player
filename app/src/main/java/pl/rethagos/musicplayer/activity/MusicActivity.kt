package pl.rethagos.musicplayer.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.rethagos.musicplayer.R

class MusicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
    }
}