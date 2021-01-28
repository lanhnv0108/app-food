package com.example.apifood.screen

import com.example.apifood.data.model.Food
import com.example.apifood.data.source.remote.OnFetchDataListener
import com.example.apifood.data.source.repository.FoodRepository
import java.lang.Exception

class HomeFoodPresenter internal constructor(private val repository: FoodRepository?): ViewContractHomeFood.Presenter {
    private var view: ViewContractHomeFood.View? = null
    override fun getMovie() {
        repository?.getFood(object :OnFetchDataListener<MutableList<Food>>{
            override fun onSuccess(data: MutableList<Food>) {
                view?.onGetViewSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }

        })

    }

    override fun onStart() {
        getMovie()
    }

    override fun onStop() {
    }

    override fun setView(view: ViewContractHomeFood.View?) {
        this.view = view
    }

}