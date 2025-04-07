package org.example.custom

import org.example.error.IError

sealed interface TUIState {
    data object Loading : TUIState
    data object UiLoaded : TUIState

    data object CloseAll : TUIState

    data class Success(val message: String) : TUIState

    data class UiError(val error: IError) : TUIState

    data object GoBack : TUIState
}