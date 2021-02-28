package pl.rethagos.musicplayer.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.rethagos.musicplayer.R
import pl.rethagos.musicplayer.model.FolderPath
import java.util.*

/**
 * RecyclerViewAdapter used to display data regarding user folders containing audio files
 */
class FolderPathAdapter(var folderPaths: ArrayList<FolderPath>, var recyclerView: RecyclerView, var listener: OnFolderPathClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_folder_path, parent, false)
        view.setOnClickListener { v: View? ->
            val currentPosition = recyclerView.getChildAdapterPosition(v!!)
            listener.onClick(folderPaths[currentPosition])
        }
        return FolderPathViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val folderPath = folderPaths[position]
        (holder as FolderPathViewHolder).setUp(folderPath)
    }

    override fun getItemCount(): Int {
        return folderPaths.size
    }

    private inner class FolderPathViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val folderNameTextView: TextView
        private val folderPathTextView: TextView
        private val folderPathNavigationButton: Button
        private var folderPath: FolderPath? = null
        fun setUp(folderPath: FolderPath) {
            this.folderPath = folderPath
            folderNameTextView.text = folderPath.folderName
            folderPathTextView.text = folderPath.folderPath
        }

        init {
            folderNameTextView = itemView.findViewById(R.id.folder_name_textview)
            folderPathTextView = itemView.findViewById(R.id.folder_path_textview)
            folderPathNavigationButton = itemView.findViewById(R.id.layout_folder_navi_button)
        }
    }

}