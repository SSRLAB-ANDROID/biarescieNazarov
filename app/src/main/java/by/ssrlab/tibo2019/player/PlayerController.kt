package by.krokam.biarescie.player

import android.media.MediaPlayer
import android.os.Handler

class PlayerController : PlayerContract.Controller {
    private var mediaPlayer: MediaPlayer? = null
    private var handler = Handler()
    private val loopRunnable = object : Runnable {
        override fun run() {
            if (isPrepared) {
                mediaPlayer?.let {
                    currentTime = parseMS(it.currentPosition)
                    currentPlayPercent = (it.currentPosition.toDouble() / it.duration.toDouble() * 100).toInt()
                }
            }

            handler.postDelayed(this, 1000)
        }
    }

    override var view: PlayerContract.View? = null

    override var currentTime = parseMS(0)
        set(value) {
            field = value
            view?.currentTime = value
        }
    override var currentPlayPercent = 0
        set(value) {
            field = value
            view?.currentPlayPercent = value
        }
    override var totalTime = parseMS(0)
        set(value) {
            field = value
            view?.totalTime = value
        }
    override var isStarted = false
        private set
    override var isPrepared = false
        private set

    override fun seekToPercent(value: Int) {
        mediaPlayer?.apply {
            seekTo((duration.toDouble() * value.toDouble() / 100).toInt())
        }
    }

    fun load(url: String) {
        isStarted = false
        isPrepared = false
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.apply {
            setDataSource(url)
            setOnPreparedListener {
                if (isStarted) {
                    startPlaying()
                }
                isPrepared = true
                totalTime = parseMS(duration)
            }
            setOnCompletionListener {
                this@PlayerController.pause()
                //stop()
                currentPlayPercent = 0
                currentTime = parseMS(0)
            }
            prepareAsync()
        }
    }
    private fun parseMS(ms: Int): String {
        return (ms / 1000).let {
            "${it / 60}:${it % 60}"
        }
    }

    override fun play() {
        if (isPrepared) {
            startPlaying()
        } else {
            isStarted = true
        }
    }

    private fun startPlaying() {
        isStarted = true
        mediaPlayer?.start()
        view?.play()
        handler.post(loopRunnable)
    }

    override fun pause() {
        mediaPlayer?.pause()
        view?.pause()
        handler.removeCallbacks(loopRunnable)
    }

    fun clear() {
        view = null
        mediaPlayer?.release()
        mediaPlayer = null
    }
}