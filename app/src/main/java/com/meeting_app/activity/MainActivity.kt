package com.meeting_app.activity

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import com.meeting_app.R
import com.meeting_app.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    private var storedVerificationId : String? = ""
    private lateinit var resendToken : PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        callbacks = object  : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                TODO("Not yet implemented")

                Log.d(Tag,"onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                TODO("Not yet implemented")

                Log.w(Tag,"onVerificationFailed",e)

                if (e is FirebaseAuthInvalidCredentialsException){
                    //Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    //The sms quota for the project has been exceeded

                } else if (e is FirebaseAuthMissingActivityForRecaptchaException){
                    // reCAPTCHA verification attempted with null Activity
                }
                // Show a message and update UI

            }
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The sms verification code has been sent to provided phone number.
                Log.d(Tag,"onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token

                // If it code sent success redirect to otp verify activity
                val intent = Intent(applicationContext,OtpVerifyNumberActivity::class.java)
                intent.putExtra("storedVerificationId",storedVerificationId)
                startActivity(intent)
                finish()
            }
        }


        // Pass mobile phone number
        binding.btnSend.setOnClickListener {
            startPhoneNumeberVerification(binding.phoneEdtTxt.text.toString())
        }
    }
    //Start sign_in_with_phone
    private fun startPhoneNumeberVerification (phoneNumber : String){
        val option = PhoneAuthOptions.newBuilder(auth)
            // Set country code
            .setPhoneNumber("+90$phoneNumber") // Phone Number to verify
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(option)
    }

    private fun  signInWithPhoneAuthCredential (credential : PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) {task->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    Log.d(Tag,"signInWithCredential: Succes")
                    startActivity(
                        Intent(
                            this,OtpVerifyNumberActivity::class.java)
                    )
                } else {
                    //Sign in failed
                    Log.w(Tag,"signInWithCredential: Failure",task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException){
                        //The verification code entered was invalid
                    }
                    //Update Ui
                }
            }
    }
    // End sign_in_with_phone
    private fun updateUi(user: FirebaseUser? = auth.currentUser) {

    }
    companion object {
        private const val Tag = "MainActivity"
    }
}