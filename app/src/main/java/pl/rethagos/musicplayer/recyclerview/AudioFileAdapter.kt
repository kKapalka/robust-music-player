package pl.rethagos.musicplayer.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.rethagos.musicplayer.R
import pl.rethagos.musicplayer.model.AudioFile
import java.util.*

/**
 * RecyclerViewAdapter used to display data regarding user folders containing audio files
 */
class AudioFileAdapter(var audioFiles: ArrayList<AudioFile>, var recyclerView: RecyclerView, var listener: OnAudioFileClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_audio_file, parent, false)
        view.setOnClickListener { v: View? ->
            val currentPosition = recyclerView.getChildAdapterPosition(v!!)
            listener.onClick(audioFiles[currentPosition])
        }
        return AudioFileViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val folderPath = audioFiles[position]
        (holder as AudioFileViewHolder).setUp(folderPath)
    }

    override fun getItemCount(): Int {
        return audioFiles.size
    }

    private inner class AudioFileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val audioFileNameTextView: TextView
        private var audioFile: AudioFile? = null
        fun setUp(audioFile: AudioFile) {
            this.audioFile = audioFile
            audioFileNameTextView.text = audioFile.title
        }

        init {
            audioFileNameTextView = itemView.findViewById(R.id.audio_file_name_text_view)
        }
    }

}