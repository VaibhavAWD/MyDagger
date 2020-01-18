package practice.mydagger.registration.enterdetails

sealed class EnterDetailsViewState
object EnterDetailsSuccess : EnterDetailsViewState()
data class EnterDetailsError(val error: String) : EnterDetailsViewState()