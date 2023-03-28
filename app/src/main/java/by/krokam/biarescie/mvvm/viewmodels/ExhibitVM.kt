package by.krokam.biarescie.mvvm.viewmodels

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.krokam.biarescie.R
import by.krokam.biarescie.data.Repository2
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.databinding.FragmentExhibitBinding
import by.krokam.biarescie.util.addTo
import by.krokam.biarescie.util.executeInBackSubOnMain
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

class ExhibitVM : ViewModel() {

    val selectedExhibit = MutableLiveData<Exhibit>()

    private var mpStatus = "play"
    private var viewModelPlayerStatus = 0

    private val mediaJob = Job()
    private val mediaScope = CoroutineScope(Dispatchers.Main + mediaJob)
    private var mediaPlayer: MediaPlayer? = null

    private var binding: FragmentExhibitBinding? = null

    var toolbarTitle = MutableLiveData<String>()
    private lateinit var mainVm: MainViewModel
    private val subs = CompositeDisposable()

    private lateinit var repo: Repository2

    fun setMainVM(mvm: MainViewModel){
        mainVm = mvm
        repo = mainVm.repo
        onCreate()
    }

    fun initializeMediaPlayer(uri: Uri, context: Context) {

        if (viewModelPlayerStatus == 0) {

            mpStatus = "play"

            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setDataSource(context, uri)
            mediaPlayer!!.prepare()

            binding!!.apPlayProgress.max = mediaPlayer!!.duration
            binding!!.apPlayTimeMax.text = convertToTimerMode(mediaPlayer!!.duration)
            binding!!.apPlayButton.setImageResource(R.drawable.ic_play_button)
            binding!!.apPlayProgress.progress = 0

            pbListener(mediaPlayer!!)

            viewModelPlayerStatus = 1
        }
    }

    fun playAudio() {
        instructMediaPlayer()
    }

    private fun mpStop() {
        mpStatus = "stop"

        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        } else mediaPlayer?.release()
    }

    private fun instructMediaPlayer() {

        mediaScope.launch {
            when (mpStatus) {

                "pause" -> {
                    mediaPlayer!!.pause()
                    binding!!.apPlayButton.setImageResource(R.drawable.ic_play_button)
                    mpStatus = "continue"
                }
                "continue" -> {

                    mediaPlayer!!.start()
                    binding!!.apPlayButton.setImageResource(R.drawable.ic_pause_button)
                    mpStatus = "pause"

                    mediaScope.launch {
                        progressListener(mediaPlayer!!)
                    }
                }
                "play" -> {

                    try {

                        mediaPlayer!!.start()

                        mediaScope.launch {
                            progressListener(mediaPlayer!!)
                        }

                        binding!!.apPlayButton.setImageResource(R.drawable.ic_pause_button)
                        mpStatus = "pause"
                    } catch (e: Exception) {
                        Log.e("Media player error", e.message.toString())
                    }
                }
            }
        }
    }

    private fun convertToTimerMode(duration: Int): String {
        val minute = duration % (1000 * 60 * 60) / (1000 * 60)
        val seconds = duration % (1000 * 60 * 60) % (1000 * 60) / 1000

        var finalString = ""
        finalString += "$minute:"
        if (seconds < 10) finalString += "0"
        finalString += "$seconds"

        return finalString
    }

    private suspend fun progressListener(
        mediaPlayer: MediaPlayer
    ) {

        while (mpStatus == "pause") {
            binding!!.apCurrentPlayTime.text = convertToTimerMode(mediaPlayer.currentPosition)
            binding!!.apPlayProgress.progress = mediaPlayer.currentPosition
            delay(250)

            if (binding!!.apPlayProgress.progress == binding!!.apPlayProgress.max) {
                mpStatus = "play"
                delay(250)
                binding!!.apPlayButton.setImageResource(R.drawable.ic_play_button)
                mediaPlayer.seekTo(0)
                binding!!.apPlayProgress.progress = 0
                binding!!.apCurrentPlayTime.text = convertToTimerMode(mediaPlayer.currentPosition)
            }
        }
    }

    private fun pbListener(mediaPlayer: MediaPlayer) {

        binding!!.apPlayProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    mediaPlayer.seekTo(p1)
                    binding!!.apCurrentPlayTime.text = convertToTimerMode(mediaPlayer.currentPosition)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    fun saveBinding(binding: FragmentExhibitBinding) {
        this.binding = binding
    }

    fun checkIfBindingSaved(): Boolean = binding == null

    fun getBinding() = binding!!

    private fun onCreate() {
        repo.selectedExhibit.executeInBackSubOnMain().subscribe {
            selectedExhibit.value = it!!
        }.addTo(subs)
    }

    override fun onCleared() {
        super.onCleared()
        subs.clear()
        mpStop()
    }
}