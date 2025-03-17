package org.example.state

import org.example.error.CustomIError
import org.example.error.IError

sealed class UIState {
    init {
        println("State changed to ${this}")
    }

    data object Loading : UIState()
    data object UiLoaded : UIState()

    data class Success(val message: String) : UIState()

    data class UiError(val error: IError) : UIState()

    data object GoBack : UIState()
}