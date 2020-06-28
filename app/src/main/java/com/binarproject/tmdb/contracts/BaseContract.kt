package com.binarproject.tmdb.contracts

class BaseContract {

    interface Presenter<T> {
        fun attach(view: T)
    }
}