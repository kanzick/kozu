package vn.kozu.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class MusicPlayerManager(context: Context) {

    private val appContext = context.applicationContext

    val player: ExoPlayer = ExoPlayer.Builder(appContext).build()

    fun playUrl(url: String) {
        if (url.isBlank()) return

        player.setMediaItem(MediaItem.fromUri(url))
        player.prepare()
        player.play()
    }

    fun pause() {
        player.pause()
    }

    fun resume() {
        player.play()
    }

    fun togglePlayPause() {
        if (player.isPlaying) {
            pause()
        } else {
            resume()
        }
    }

    fun playLocalResource(resourceId: Int) {
        player.setMediaItem(
            MediaItem.fromUri(
                "android.resource://${appContext.packageName}/$resourceId"
            )
        )
        player.prepare()
        player.play()
    }
    fun release() {
        player.release()
    }
}