package com.example.psequalizer

import android.media.audiofx.Equalizer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var equalizer: Equalizer? = null
    private lateinit var tvStatus: TextView
    private lateinit var btnBypass: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        btnBypass = findViewById(R.id.btnBypass)

        try {
            equalizer = Equalizer(0, 0).apply {
                enabled = true
            }
            tvStatus.text = "PSequalizer Terhubung ke Sistem"
        } catch (e: Exception) {
            tvStatus.text = "Equalizer tidak didukung pada perangkat ini"
        }

        var isEnabled = true
        btnBypass.setOnClickListener {
            isEnabled = !isEnabled
            equalizer?.enabled = isEnabled
            btnBypass.text = if (isEnabled) "Bypass / Matikan Efek" else "Nyalakan Kembali"
            tvStatus.text = if (isEnabled) "PSequalizer Aktif" else "PSequalizer Dibypass"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        equalizer?.release()
    }
}
