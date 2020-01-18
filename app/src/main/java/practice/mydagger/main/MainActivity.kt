package practice.mydagger.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import practice.mydagger.MyApplication
import practice.mydagger.R
import practice.mydagger.login.LoginActivity
import practice.mydagger.registration.RegistrationActivity
import practice.mydagger.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userManager = (application as MyApplication).userManager
        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                startActivity(Intent(this, RegistrationActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        } else {
            setContentView(R.layout.activity_main)

            mainViewModel = MainViewModel(userManager.userDataRepository!!)
            setupViews()
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<TextView>(R.id.display_notifications).text = mainViewModel.notificationsText
    }

    private fun setupViews() {
        findViewById<TextView>(R.id.display_welcome).text = mainViewModel.welcomeText

        findViewById<Button>(R.id.btn_settings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
