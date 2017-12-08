package hu.hanprog.hunyadym.christmasapp

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SoundPool.OnLoadCompleteListener {
    private val MAX_NUMBER_STREAMS = 2
    private var VOLUME = 100.0f
    private val SOURCE_QUALITY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val actualVolume = audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
        val maxVolume = audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        VOLUME = actualVolume / maxVolume
        val soundPool = SoundPool(MAX_NUMBER_STREAMS, AudioManager.STREAM_MUSIC, SOURCE_QUALITY).apply {
            setOnLoadCompleteListener(this@MainActivity)
        }
        val bellId = soundPool.load(this, R.raw.csengo, 1)
        //     val musicId = soundPool.load(this, R.raw.mennybol, 1)

        playerStatus.text = "idle"
        btnBell.setOnClickListener {
            soundPool.play(bellId, VOLUME, VOLUME, 1, 1, 1.0f)
            playerStatus.text = "bell"
            //  btnBell.isEnabled = false
        }

        btnMusic.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.mennybol);
            mediaPlayer.start();
            playerStatus.text = "music"
            //  btnMusic.isEnabled = false
        }
    }

    override fun onLoadComplete(soundPool: SoundPool?, sampleId: Int, status: Int) {

    }
}
