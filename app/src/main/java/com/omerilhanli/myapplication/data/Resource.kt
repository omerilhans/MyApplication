package com.omerilhanli.myapplication.data

data class Resource<T>(val status: Status, val data: T? = null) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(data: T): Resource<T> {
            return Resource(Status.ERROR, data)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR
}