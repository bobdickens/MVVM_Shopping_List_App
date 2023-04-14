package com.example.mvvmshoppinglistapp

import androidx.appcompat.app.AppCompatDialog

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}