package practice.mydagger.registration.enterdetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_enter_details.*
import practice.mydagger.MyApplication

import practice.mydagger.R
import practice.mydagger.registration.RegistrationActivity
import practice.mydagger.registration.RegistrationViewModel
import javax.inject.Inject

class EnterDetailsFragment : Fragment() {

    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    @Inject
    lateinit var enterDetailsViewModel: EnterDetailsViewModel

    private lateinit var displayError: TextView
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as RegistrationActivity).registrationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enter_details, container, false)

        enterDetailsViewModel.enterDetailsState.observe(this, Observer { state ->
            when (state) {
                is EnterDetailsSuccess -> {
                    val username = edtUsername.text.toString()
                    val password = edtPassword.text.toString()
                    registrationViewModel.updateUserData(username, password)

                    (activity as RegistrationActivity).onDetailsEntered()
                }
                is EnterDetailsError -> {
                    displayError.text = state.error
                    displayError.visibility = View.VISIBLE
                }
            }
        })

        setupViews(view)

        return view
    }

    private fun setupViews(view: View) {
        displayError = view.findViewById(R.id.display_error)
        edtUsername = view.findViewById(R.id.edt_username)
        edtPassword = view.findViewById(R.id.edt_password)

        edtUsername.doOnTextChanged { _, _, _, _ ->
            displayError.visibility = View.INVISIBLE
        }

        edtPassword.doOnTextChanged { _, _, _, _ ->
            displayError.visibility = View.INVISIBLE
        }

        view.findViewById<Button>(R.id.btn_next)?.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            enterDetailsViewModel.validateInput(username, password)
        }
    }

}
