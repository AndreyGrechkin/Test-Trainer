package com.defey.testtrainer.ui.workout

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import jakarta.inject.Inject

// TODO: 'класс сгенерирован с помощью AI'
class SoundPlayer @Inject constructor(private val context: Context) {

    private var ringtone: Ringtone? = null

    fun playSingleBeep() {
        playSystemSound(RingtoneManager.TYPE_NOTIFICATION)
    }

    fun playDoubleBeep() {
        playSystemSound(RingtoneManager.TYPE_ALARM)
    }

    private fun playSystemSound(type: Int) {
        val uri = RingtoneManager.getDefaultUri(type)
        ringtone?.stop()
        ringtone = RingtoneManager.getRingtone(context, uri)
        ringtone?.play()
    }
}
