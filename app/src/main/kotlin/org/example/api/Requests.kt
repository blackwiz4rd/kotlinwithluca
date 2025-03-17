package org.example.api

sealed interface ApiRequest {
    data object LoginRequest : ApiRequest
    data object LogoutRequest : ApiRequest
}
