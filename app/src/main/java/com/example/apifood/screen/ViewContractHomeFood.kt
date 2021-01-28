package com.example.apifood.screen

import com.example.apifood.data.model.Food
import com.example.apifood.utils.BasePresenter
import java.lang.Exception

interface ViewContractHomeFood {
    /**
     * View
     */
    interface View{
        fun onGetViewSuccess(foods: MutableList<Food>)
        fun onError(exception: Exception?)
    }
    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View>{
        /**
         * from repository
         */
        fun getMovie()
    }
}