package practice.mydagger.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import practice.mydagger.MyApplication
import practice.mydagger.R
import practice.mydagger.main.MainActivity
import practice.mydagger.registration.RegistrationActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private lateinit var displayError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creates an instance of LoginComponent by grabbing the factory from the app graph
        (application as MyApplication).appComponent
            .loginComponent()
            .create()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel.loginState.observe(this, Observer { state ->
            when (state) {
                is LoginSuccess -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is LoginError -> displayError.visibility = View.VISIBLE
            }
        })

        setupViews()
    }

    private fun setupViews() {
        displayError = findViewById(R.id.display_error)

        val edtUsername = findViewById<EditText>(R.id.edt_username)
        edtUsername.isEnabled = false
        edtUsername.setText(loginViewModel.getUsername())

        val edtPassword = findViewById<EditText>(R.id.edt_password)
        edtPassword.doOnTextChanged { _, _, _, _ ->
            displayError.visibility = View.INVISIBLE
        }

        btn_login.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            loginViewModel.login(username, password)
        }

        btn_unregister.setOnClickListener {
            loginViewModel.unregister()
            Intent(this, RegistrationActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            }.also {
                startActivity(it)
            }
        }
    }
}
