package com.example.esisaandroid.ui.uyelikler.bireyselAliciUyelik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.esisaandroid.databinding.ActivityBireyselAliciUyelikBinding
import com.example.esisaandroid.ui.girisler.bireyselAliciGiris.GirisActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BireyselAliciUyelikActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBireyselAliciUyelikBinding
    private lateinit var auth:FirebaseAuth
    private var databaseReference:DatabaseReference?=null
    private var database:FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityBireyselAliciUyelikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        //Kaydet butonu ile kaydetme adımları
        binding.uyekaydetbuton.setOnClickListener {
            var uyeadsoyad = binding.uyeadsoyad.text.toString()
            var uyemail = binding.uyemail.text.toString()
            var uyeparola = binding.uyeparola.text.toString()
            if (TextUtils.isEmpty(uyeadsoyad)){
                binding.uyeadsoyad.error = "Lütfen adınızı ve soyadınızı giriniz"
                return@setOnClickListener
            }else if (TextUtils.isEmpty(uyemail)) {
                binding.uyemail.error = "Lütfen mailinizi adresinizi giriniz"
                return@setOnClickListener
            }else if (TextUtils.isEmpty(uyeparola)) {
                binding.uyeparola.error = "Lütfen parolanızı giriniz"
                return@setOnClickListener
            }
            //E mail parola ve kullanıcı bilgilerini veritabanına ekleme
            auth.createUserWithEmailAndPassword(binding.uyemail.text.toString() , binding.uyeparola.text.toString())
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        //şu anki kullanıcı bilgilerini alalım
                        var currentUser = auth.currentUser
                        //Kullanıcı idsini alıp o id adı altında adımızı ve soyadımızı kaydedelim
                        var currentUserDb = currentUser?.let { it1 -> databaseReference?.child(it1.uid) }
                        currentUserDb?.child("adi soyadi")?.setValue(binding.uyeadsoyad.text.toString())
                        Toast.makeText(this@BireyselAliciUyelikActivity , "Kaydınız başarılı Giriş Yap butonuna tıklayınız" , Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@BireyselAliciUyelikActivity , "Kaydınız oluşturulamadı" , Toast.LENGTH_LONG).show()
                    }

                }
        }
        //Giriş sayfasına gitmek için
        binding.uyegirisyapbuton.setOnClickListener {
            intent = Intent(applicationContext, GirisActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}