package com.android.fruitlistapplication.base


import com.android.fruitlistapplication.network.APIEndService
import io.reactivex.disposables.CompositeDisposable

interface BaseContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError(error: FruitSyncError)
    }

    interface Presenter {
        fun onDestroy()
    }

    interface Interactor {
        fun clear()
    }
}

open class BaseInteractor(apiEndService: APIEndService) : BaseContract.Interactor {
    protected val apiService: APIEndService = apiEndService

    protected val compositeDisposable = CompositeDisposable()
    override fun clear() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
    }

}
