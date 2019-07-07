package com.android.fruitlistapplication

import android.annotation.SuppressLint
import com.android.fruitlistapplication.base.*
import com.android.fruitlistapplication.constants.AppConstants
import com.android.fruitlistapplication.model.*
import com.android.fruitlistapplication.network.APIEndService
import com.android.fruitlistapplication.room.AppDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface FruitsContract {
    interface View : BaseContract.View {
        fun updateFruits(FruitItem: List<FruitItem>)
    }

    interface Presenter : BaseContract.Presenter {
        fun fetchFruitsList()
        fun sortFruitsList(FruitItem: List<FruitItem>): List<FruitItem>
    }

    interface Interactor : BaseContract.Interactor {
        fun fetchFruitsList(listener: APIListener<List<FruitItem>>)
        fun fetchFruitsFromDatabase(listener: DataFetchListener<List<FruitItem>>)
    }
}

class FruitsPresenter(val view: FruitsContract.View, roomDatabase: AppDataBase, apiService: APIEndService) :
    FruitsContract.Presenter {
    /**
     * Sorted Fruits List method - will sort Fruits list based on Fruits title
     */
    override fun sortFruitsList(FruitItem: List<FruitItem>): List<FruitItem> {
        return FruitItem.sortedBy { it -> it.type }

    }

    private val fruitsInteractor = FruitsInteractor(roomDatabase, apiService)
    /**
     * fetchFruitsList method, will fetch Fruits details from remote server and store
     * into local storage and give final sorted list into view
     */
    override fun fetchFruitsList() {
        view.showProgress()
        fruitsInteractor.fetchFruitsFromDatabase(object : DataFetchListener<List<FruitItem>> {
            override fun onSuccess(dataModel: List<FruitItem>) {
                if (dataModel.isNotEmpty()) {
                    view.hideProgress()
                    view.updateFruits(sortFruitsList(dataModel))
                }
            }
        })
        fruitsInteractor.fetchFruitsList(object : APIListener<List<FruitItem>> {
            override fun onSuccess(dataModel: List<FruitItem>) {
                view.hideProgress()
                view.updateFruits(sortFruitsList(dataModel))
            }

            override fun onError(error: FruitSyncError) {
                view.hideProgress()
                view.showError(error)
            }
        })
    }

    override fun onDestroy() {
        fruitsInteractor.clear()
    }
}


class FruitsInteractor(val dataBase: AppDataBase, apiService: APIEndService) : BaseInteractor(apiService),
    FruitsContract.Interactor {
    /**
     * fetchFruitsFromDatabase method will fetch Fruits data from local storage
     */
    @SuppressLint("CheckResult")
    override fun fetchFruitsFromDatabase(listener: DataFetchListener<List<FruitItem>>) {
        val FruitsRequest = dataBase.FruitDao().getAll().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
        FruitsRequest.doOnSubscribe { compositeDisposable.add(it) }
            .subscribe { listener.onSuccess(it) }
    }

    /**
     * fetchFruitsList method will fetch Fruits data from server, and store into local storage
     */

    override fun fetchFruitsList(listener: APIListener<List<FruitItem>>) {
        compositeDisposable.add(
            apiService.fruitsList()
                .doOnSuccess { dataBase.FruitDao().insertAll(extractFruitsList(it)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener.onSuccess(extractFruitsList(it)) },
                    { listener.onError(FruitSyncError(it, "", AppConstants.SERVER_ERROR)) })
        )

    }


    private fun extractFruitsList(it: Response?): List<FruitItem> {
        val fruitDataList = ArrayList<FruitItem>()

        val mainDataList = it?.fruit as List<FruitItem>
        for (i in mainDataList.indices) {
            val item = mainDataList[i]
            val price: Float = ((1000 / item.weight) * item.price) / 100
            fruitDataList.add(FruitItem(item.price, item.weight, item.type, price, i))
        }
        return fruitDataList
    }

    override fun clear() {
        compositeDisposable.clear()
    }

}