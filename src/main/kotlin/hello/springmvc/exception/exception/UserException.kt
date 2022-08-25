package hello.springmvc.exception.exception

class UserException: RuntimeException {

    constructor(message: String): super(message)

    constructor(message: String, cause: Throwable): super(message, cause)

    constructor(cause: Throwable): super(cause)

    constructor(
        message: String,
        cause: Throwable,
        enableSuppression: Boolean,
        writeableStackTrace: Boolean
    ): super(
        message, cause, enableSuppression, writeableStackTrace,
    )

}
