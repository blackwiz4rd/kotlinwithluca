// The SDK-provided interface (possibly from a 3rd-party lib)
interface MyInterface {
    fun bar(callback: Callback)
    fun foo() = "foo"
}

// Your internal wrapper for implementation detail
@JvmInline
internal value class SdkInterfaceWrapper(val myInterface: MyInterface) : MyInterface by myInterface

// Simple callback structure for your clean API
interface DoSomethingCallback {
    fun onSuccess(message: String)
    fun onFailure(error: Throwable)
}

// Your public-facing object or manager
object SdkFacade {

    // Internal delegate — user never sees this
    private val wrapped = SdkInterfaceWrapper(object : MyInterface {
        override fun bar(callback: Callback) {
            // Simulate success/failure logic
            val success = true
            if (success) {
                callback.onSuccess("Operation completed successfully.")
            } else {
                callback.onFailure(RuntimeException("Something went wrong."))
            }
        }
    })

    // Public method exposed to SDK clients
    fun doSomething(callback: DoSomethingCallback) {
        wrapped.bar(object : Callback {
            override fun onSuccess(result: String) {
                callback.onSuccess(result)
            }

            override fun onFailure(error: Throwable) {
                callback.onFailure(error)
            }
        })
    }
}

// Low-level callback used internally
interface Callback {
    fun onSuccess(result: String)
    fun onFailure(error: Throwable)
}

// Usage — this is what your user sees
fun callSdkExample() {
    SdkFacade.doSomething(object : DoSomethingCallback {
        override fun onSuccess(message: String) {
            println("✅ Client got success: $message")
        }

        override fun onFailure(error: Throwable) {
            println("❌ Client got error: ${error.message}")
        }
    })
}