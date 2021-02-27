package pl.rethagos.musicplayer.recyclerview

import pl.rethagos.musicplayer.model.FolderPath

interface OnFolderPathClickListener {
    fun onClick(folderPath: FolderPath?)
}