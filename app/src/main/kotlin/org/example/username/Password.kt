//Feature	Inline/Value Class	Regular Class
//Runtime object	No (unboxed)	Yes (boxed)
//Type safety	Yes	Yes
//Performance	Better (fewer allocations)	May involve overhead
//Constraints	Only one property allowed	Multiple properties

typealias PasswordAlias = String

@JvmInline
value class Password(val s: String) {
    init {
        require(s.isNotEmpty()) {
            "Password is required to login"
        }
    }
}