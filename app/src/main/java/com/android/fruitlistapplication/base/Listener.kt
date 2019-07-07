package com.android.fruitlistapplication.base

interface APIListener<T> : DataFetchListener<T> {
    fun onError(error: FruitSyncError)
}

interface DataFetchListener<T> {
    fun onSuccess(dataModel: T)
}

interface OnClick<T> {
    fun onClick(dataModel: T)
}