package com.meeting_app.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import com.meeting_app.R
import com.meeting_app.databinding.ActivityMainBinding
import com.meeting_app.databinding.ActivityOtpVerifyNumberBinding

class OtpVerifyNumberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpVerifyNumberBinding
    private var storeVerification : String? =""
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerifyNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= Firebase.auth
        storeVerification = intent.getStringExtra("storedVerificationId")
        binding.btnVerify.setOnClickListener {
            verifyPhoneNumberWithCode(storeVerification,binding.pinEdtTxt.text.toString())

        }

    }
    private fun verifyPhoneNumberWithCode(verificationId : String?, code : String){
        // Start verify with code
        val credential = PhoneAuthProvider.getCredential(verificationId!!,code)
        // End verify with code
        signInWithPhoneAuthCredential(credential)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful){
                    val user = task.result?.user
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("succes", "signInWithCredential:success")
                    // Pass user value to home if u need
                    Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@OtpVerifyNumberActivity,MainActivity2::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    // Sign in failed
                    Log.w("failed", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException){
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}