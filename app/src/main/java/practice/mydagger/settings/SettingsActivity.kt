package practice.mydagger.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import practice.mydagger.MyApplication
import practice.mydagger.R
import practice.mydagger.login.LoginActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val userManager = (application as MyApplication).userManager

        settingsViewModel = SettingsViewModel(userManager.userDataRepository!!, userManager)
        setupViews()
    }

    private fun setupViews() {
        btn_refresh.setOnClickListener {
            settingsViewModel.refreshNotifications()
        }

        btn_logout.setOnClickListener {
            settingsViewModel.logout()
            Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            }.also {
                startActivity(it)
            }
        }
    }
}
