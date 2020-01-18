package practice.mydagger.login

sealed class LoginViewState
object LoginSuccess : LoginViewState()
object LoginError : LoginViewState()