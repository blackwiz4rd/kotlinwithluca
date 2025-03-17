package org.example.error

// Create a sealed interface
sealed interface IError {

    // Create a singleton object implementing the 'Error' sealed interface
    object RuntimeIError : IError
}

// Create a sealed class that implements sealed interface Error
sealed class IOIError(): CustomIError() {
    // this class is abstract and cannot be instanciated

    // Define subclasses that extend sealed class 'IOError'
    class FileReadIError(val file: String): IOIError()
    class DatabaseIError(val source: String): IOIError()
}

sealed class AppIError(val message: String, val severity: IErrorSeverity) : CustomIError() {

    enum class IErrorSeverity : IError { MINOR, MAJOR, CRITICAL }

    class NetworkIError(): AppIError("Network issue", IErrorSeverity.MAJOR)
    class DatabaseIError(val databaseCorrupted: Boolean): AppIError("Database issue", IErrorSeverity.CRITICAL)
    class UnknownIError(): AppIError("Unknown issue", IErrorSeverity.MINOR)
}

open class CustomIError(): IError
