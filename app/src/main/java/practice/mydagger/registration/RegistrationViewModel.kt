package practice.mydagger.registration

import practice.mydagger.di.ActivityScope
import practice.mydagger.user.UserManager
import javax.inject.Inject

@ActivityScope
class RegistrationViewModel @Inject constructor(val userManager: UserManager) {

    private var username: String? = null
    private var password: String? = null
    private var acceptedTCs: Boolean? = null

    fun updateUserData(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun acceptTCs() {
        acceptedTCs = true
    }

    fun registerUser() {
        assert(username != null)
        assert(password != null)
        assert(acceptedTCs != null)
        userManager.registerUser(username!!, password!!)
    }
}