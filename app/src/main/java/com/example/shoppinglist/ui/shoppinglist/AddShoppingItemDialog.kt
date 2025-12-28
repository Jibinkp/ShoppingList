package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem

class AddShoppingItemDialog(context: Context, val addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {
    private lateinit var tvCancel: TextView
    private lateinit var tvAdd: TextView
    private lateinit var etName: EditText
    private lateinit var etAmount: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tvCancel = findViewById(R.id.tvCancel)!!
        tvAdd = findViewById(R.id.tvAdd)!!
        etName = findViewById(R.id.etName)!!
        etAmount = findViewById(R.id.etAmmount)!!

        tvCancel.setOnClickListener {
            cancel()
        }

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(
                name,
                amount.toInt()
            )
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
    }
}