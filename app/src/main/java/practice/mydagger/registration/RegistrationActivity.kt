package practice.mydagger.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import practice.mydagger.main.MainActivity
import practice.mydagger.MyApplication
import practice.mydagger.R
import practice.mydagger.registration.enterdetails.EnterDetailsFragment
import practice.mydagger.registration.termsandconditions.TermsAndConditionsFragment

class RegistrationActivity : AppCompatActivity() {

    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationViewModel = RegistrationViewModel((application as MyApplication).userManager)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, EnterDetailsFragment())
            .commit()
    }

    fun onDetailsEntered() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, TermsAndConditionsFragment())
            .addToBackStack(TermsAndConditionsFragment::class.java.simpleName)
            .commit()
    }

    fun onTermsAndConditionsAccepted() {
        registrationViewModel.registerUser()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
