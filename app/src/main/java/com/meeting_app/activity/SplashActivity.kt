package com.meeting_app.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.meeting_app.R

class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        if (isConnected() == false){
            var builder = AlertDialog.Builder(this)
            builder.setTitle("No Internet Connection")
            builder.setMessage("Do you wanna exit ?")

            builder.setPositiveButton("Exit"){dialog, which ->
                finish()
                System.exit(0)
            }
            builder.setNegativeButton("Check Wifi"){dialog, which ->
                startActivity(Intent(
                    Settings.ACTION_WIFI_SETTINGS
                    )
                )
                finish()
                System.exit(0)

            }
            var alertDialog : AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        } else {
            handler = Handler()
            handler.postDelayed({
                startActivity(
                    Intent(
                        this, MainActivity::class.java
                    )
                )
                finish()
            }, 1750)
        }
    }

    fun isConnected(): Boolean {
        var connectivityManager : ConnectivityManager =
            getSystemService(Context.
                CONNECTIVITY_SERVICE) as ConnectivityManager

        var networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo!=null) {
            if (networkInfo.isConnected){
                return true
            }
        }
        return false
    }
}