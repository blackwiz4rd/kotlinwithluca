package org.example.api

// Define the ApiResponse sealed class with detailed response types
sealed class ApiResponse {
    data class UserSuccess(val user: UserData) : ApiResponse()
    data object UserNotFound : ApiResponse()
    data object UserLoggedOut : ApiResponse()
    data class Error(val message: String) : ApiResponse()
}

// User data class to be used in the success response
open class UserData(open val userId: Int, open val username: String)
data class User(override val userId: Int, override val username: String, val password: String) : UserData(userId, username) {
    fun isValid() = username == "validUser" && password == "validPass" && userId >= 0

    private var _isLogged = false

    val isLogged
        get() = _isLogged

    fun handleRequest(request: ApiRequest): ApiResponse {
        return when (request) {
            is ApiRequest.LoginRequest -> {
                if (isValid()) {
                    _isLogged = true
                ApiResponse.UserSuccess(this)
            } else {
                _isLogged = false
                ApiResponse.UserNotFound
            }}
            ApiRequest.LogoutRequest -> {
                _isLogged= false
                ApiResponse.UserLoggedOut
            }
        }
    }
}