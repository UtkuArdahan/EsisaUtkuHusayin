package com.example.esisaandroid.ui.satici.saticianasayfa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.esisaandroid.R
import com.example.esisaandroid.databinding.ActivitySaticiAnasayfaBinding
import com.example.esisaandroid.ui.satici.saticiUrunEkleme.SaticiUrunEklemeActivity

class SaticiAnasayfaActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySaticiAnasayfaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnProductAdd.setOnClickListener {
            val intent= Intent(applicationContext, SaticiUrunEklemeActivity::class.java)
        }
    }
}