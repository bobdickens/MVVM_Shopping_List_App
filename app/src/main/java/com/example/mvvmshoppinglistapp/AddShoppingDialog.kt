package com.example.mvvmshoppinglistapp
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.mvvmshoppinglistapp.databinding.DialogAddShoppingItemBinding

class AddShoppingDialog(
    context: Context, var addDialogListener: AddDialogListener
): AppCompatDialog(context)
{
    private lateinit var binding: DialogAddShoppingItemBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            //val amount = binding.etAmount.toString()

            if(name.isEmpty() /*|| amount.isEmpty()*/){
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name/*, amount.toInt()*/, 1)

            addDialogListener.onAddButtonClicked(item)
            dismiss()

            binding.tvCancel.setOnClickListener {
                cancel()
            }
        }


    }
}