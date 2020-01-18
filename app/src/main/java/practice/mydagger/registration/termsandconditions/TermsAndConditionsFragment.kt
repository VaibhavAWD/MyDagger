package practice.mydagger.registration.termsandconditions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_terms_and_conditions.*
import practice.mydagger.R
import practice.mydagger.registration.RegistrationActivity
import practice.mydagger.registration.RegistrationViewModel

class TermsAndConditionsFragment : Fragment() {

    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false)

        registrationViewModel = (activity as RegistrationActivity).registrationViewModel

        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            registrationViewModel.acceptTCs()
            (activity as RegistrationActivity).onTermsAndConditionsAccepted()
        }

        return view
    }

}
