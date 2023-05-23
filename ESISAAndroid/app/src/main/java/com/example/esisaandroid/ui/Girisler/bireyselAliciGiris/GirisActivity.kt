package com.example.esisaandroid.ui.girisler.bireyselAliciGiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.esisaandroid.databinding.ActivityGirisBinding
import com.example.esisaandroid.ui.bireyselAlici.bireyselAliciAnasayfa.BireyselAliciAnasayfaActivity
import com.example.esisaandroid.ui.kurumsalAlici.kurumsalAliciAnasayfa.KurumsalAliciAnasayfaActivity
import com.example.esisaandroid.ui.satici.saticianasayfa.SaticiAnasayfaActivity
import com.example.esisaandroid.ui.uyelikler.bireyselAliciUyelik.BireyselAliciUyelikActivity
import com.google.firebase.auth.FirebaseAuth

class GirisActivity : AppCompatActivity() {
    private lateinit var binding:ActivityGirisBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        //Satıcı butonuna tıklandığında yapılacaklar
        binding.saticigirisbuton.setOnClickListener {
            val saticiemail = binding.girisemail.text.toString()
            val saticiparola = binding.girisparola.text.toString()
            if (TextUtils.isEmpty(saticiemail)) {
                binding.girisemail.error = "Lütfen e-mailinizi giriniz"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(saticiparola)) {
                binding.girisparola.error = "Lütfen parolanızı giriniz"
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(saticiemail, saticiparola)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        val saticiintent = Intent(applicationContext, SaticiAnasayfaActivity::class.java)
                        startActivity(saticiintent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Yanlış e-mail ya da parola lütfen tekrar deneyiniz",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        //kurumsal butonuna tıklandığında yapılacaklar
        binding.kurumsalgirisbuton.setOnClickListener {
            val kurumsalemail = binding.girisemail.text.toString()
            val kurumsalparola = binding.girisparola.text.toString()
            if (TextUtils.isEmpty(kurumsalemail)) {
                binding.girisemail.error = "Lütfen e-mailinizi giriniz"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(kurumsalparola)) {
                binding.girisparola.error = "Lütfen parolanızı giriniz"
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(kurumsalemail, kurumsalparola)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        val kurumsalintent = Intent(applicationContext, KurumsalAliciAnasayfaActivity::class.java)
                        startActivity(kurumsalintent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Yanlış e-mail ya da parola lütfen tekrar deneyiniz",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
//bireysel giriş butonuna tıklandığında yapılacaklar
        binding.bireyselgirisbuton.setOnClickListener {
            val email =binding.girisemail.text.toString()
            val parola =binding.girisparola.text.toString()
            if (TextUtils.isEmpty(email)){
                binding.girisemail.error="Lütfen e-mailinizi giriniz"
                return@setOnClickListener
            }else if (TextUtils.isEmpty(parola)){
                binding.girisparola.error="Lütfen parolanızı giriniz"
                return@setOnClickListener
            }
            //giriş bilgilerini doğrulayıp giriş yapacağız
            auth.signInWithEmailAndPassword(email,parola)
                .addOnCompleteListener(this) {
                if (it.isSuccessful){
                   val intent=Intent(applicationContext,BireyselAliciAnasayfaActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Yanlış e-mail ya da parola lütfen tekrar deneyiniz",Toast.LENGTH_LONG).show()
                }
            }
        }
        //yeni üyelik butonuna tıklanınca
        binding.yeniuyelikbuton.setOnClickListener {
            intent=Intent(applicationContext,BireyselAliciUyelikActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}