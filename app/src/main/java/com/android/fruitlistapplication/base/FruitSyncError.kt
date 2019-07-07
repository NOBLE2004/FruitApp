package com.android.fruitlistapplication.base

class FruitSyncError : Exception {
    private var errorMessage: String? = null
    private var errorCode: Int? = null

    constructor(message: String?, errorMessage: String?, errorCode: Int?) : super(message) {
        this.errorMessage = errorMessage
        this.errorCode = errorCode
    }

    constructor(cause: Throwable?, errorMessage: String?, errorCode: Int?) : super(errorMessage, cause) {
        this.errorMessage = errorMessage
        this.errorCode = errorCode
    }
}