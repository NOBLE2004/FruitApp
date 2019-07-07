package com.android.fruitlistapplication


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.fruitlistapplication.network.APIEndService
import com.android.fruitlistapplication.base.FruitSyncError
import com.android.fruitlistapplication.base.BaseFragment
import com.android.fruitlistapplication.base.OnClick
import com.android.fruitlistapplication.model.FruitItem
import com.android.fruitlistapplication.room.AppDataBase
import com.android.fruitlistapplication.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), FruitsContract.View {
    override fun updateFruits(FruitItem: List<FruitItem>) {
        fruitList.clear()
        fruitList.addAll(FruitItem)
        fruitAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView?.visibility = View.GONE
    }

    override fun showError(error: FruitSyncError) {
        Utils.showError(activity!!, getString(R.string.server_error))
    }

    @Inject
    lateinit var apiEndService: APIEndService
    private var presenter: FruitsPresenter? = null
    @Inject
    lateinit var database: AppDataBase
    private val fruitList = ArrayList<FruitItem>()
    private val fruitAdapter = FruitAdapter(fruitList, object : OnClick<FruitItem> {
        override fun onClick(dataModel: FruitItem) {
            val bundle = Bundle()
            bundle.putSerializable("data", dataModel)
            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = bundle
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.hide(fragmentManager!!.findFragmentByTag("HomeFragment")!!)
            fragmentTransaction.add(R.id.baseContainer, detailsFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FruitsPresenter(this, database, apiEndService)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter?.fetchFruitsList()
        rvFruit.layoutManager = GridLayoutManager(activity, 2)
        rvFruit.adapter = fruitAdapter
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}