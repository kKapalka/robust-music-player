package pl.rethagos.musicplayer.recyclerview

import pl.rethagos.musicplayer.model.AudioFile

interface OnAudioFileClickListener {
    fun onClick(audioFile: AudioFile?)
}