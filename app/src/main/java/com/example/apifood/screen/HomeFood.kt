package com.example.apifood.screen

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apifood.R
import com.example.apifood.data.model.Food
import com.example.apifood.data.source.repository.FoodRepository
import com.example.apifood.screen.adapter.AdapterHomeFood
import com.example.apifood.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class HomeFood : AppCompatActivity(), ViewContractHomeFood.View , OnItemRecyclerViewClickListener<Food>{
    private val adapter: AdapterHomeFood by lazy { AdapterHomeFood() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        recyclerViewFood.setHasFixedSize(true)
        recyclerViewFood.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)
    }
    private fun initData() {
        val presenter =  HomeFoodPresenter(FoodRepository.instance)
        presenter.setView(this)
        presenter.onStart()

    }

    override fun onGetViewSuccess(foods: MutableList<Food>) {
        adapter.updateData(foods)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClickListener(item: Food?) {
        Toast.makeText(this, item?.title, Toast.LENGTH_LONG).show()
    }

}