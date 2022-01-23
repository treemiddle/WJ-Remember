package com.jay.common

sealed class ErrorException(message: String, val code: Int) : Throwable(message) {
    class NetworkException(message: String, code: Int): ErrorException(message, code)
}