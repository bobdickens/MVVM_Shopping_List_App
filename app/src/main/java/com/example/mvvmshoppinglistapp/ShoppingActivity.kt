package com.example.mvvmshoppinglistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshoppinglistapp.databinding.ActivityShoppingBinding


class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ShoppingDatabase(this)

        val repository = ShoppingRepository(database)

        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        binding.rvRecycler.layoutManager = LinearLayoutManager(this)
        binding.rvRecycler.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddShoppingDialog(this, object : AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                   viewModel.upsert(item)
                }
            }).show()
        }

    }
}