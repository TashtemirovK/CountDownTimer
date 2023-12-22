package com.example.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.countdowntimer.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnPlay.setOnClickListener {
                startCountDownTimer(20000)
            }
        }
    }

    private fun startCountDownTimer(timeMillis: Long) {
        timer?.cancel()

        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                timer = object : CountDownTimer(timeMillis, 1000) {
                    override fun onTick(timeM: Long) {
                        binding.tvCount.text = (timeM / 1000).toString()
                    }

                    override fun onFinish() {
                        binding.tvCount.text = "Finish"
                    }
                }.start()
            }
        }
    }
}
