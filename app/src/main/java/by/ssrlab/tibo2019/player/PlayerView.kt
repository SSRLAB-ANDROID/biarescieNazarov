package by.krokam.biarescie.player

import android.content.Context
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import by.krokam.biarescie.R
import by.krokam.biarescie.util.isGone
import kotlinx.android.synthetic.main.view_player.view.*


class PlayerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs), PlayerContract.View {


    private val playIcon: ImageView
    private val pauseIcon: ImageView
    private val tvCurrentTime: TextView
    private val tvAllTime: TextView
    private val seekBar: AppCompatSeekBar

    private var controller: PlayerContract.Controller? = null

    override var currentTime: String = "0:0"
        set(value) {
            field = value
            tvCurrentTime.text = value
        }

    override var totalTime: String = "0:0"
        set(value) {
            field = value
            tvAllTime.text = value
        }

    override var currentPlayPercent: Int = 0
        set(value) {
            field = value
            seekBar.progress = value
        }


    init {
        LayoutInflater.from(context).inflate(R.layout.view_player, this, true)
        playIcon = icPlay
        pauseIcon = icStop
        tvCurrentTime = tvCurTime
        tvAllTime = findViewById(R.id.tvAllTime)
        seekBar = seekBarView.apply {
            max = 100
        }
        setPlayingState(false)
    }

    fun attachController(cont: PlayerContract.Controller) {
        cont.view = this
        setPlayingState(cont.isStarted)
        currentTime = cont.currentTime
        totalTime = cont.totalTime
        currentPlayPercent = cont.currentPlayPercent

        playIcon.setOnClickListener { cont.play() }
        pauseIcon.setOnClickListener { cont.pause() }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {}

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {
                p0?.let{
                    cont.seekToPercent(it.progress)
                }
            }
        })
    }

    override fun play() {
        setPlayingState(true)
    }

    override fun pause() {
        setPlayingState(false)
    }

    private fun setPlayingState(state: Boolean) {
        icPlay.isGone(state)
        icStop.isGone(!state)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        controller?.view = null
    }
}