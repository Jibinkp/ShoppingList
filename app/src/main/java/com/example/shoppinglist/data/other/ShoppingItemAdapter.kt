package com.example.shoppinglist.data.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ShoppingViewHolder,
        position: Int
    ) {
        val currentShoppingItem = items[position]
        holder.tvName.text = currentShoppingItem.name
        holder.tvAmount.text = "${currentShoppingItem.amount}"

        holder.ivDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }

        holder.ivPlus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }

        holder.ivMinus.setOnClickListener {
            if (currentShoppingItem.amount > 0) {
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount = itemView.findViewById<TextView>(R.id.tvAmount)
        val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
        val ivPlus = itemView.findViewById<ImageView>(R.id.ivPlus)
        val ivMinus = itemView.findViewById<ImageView>(R.id.ivMinus)
    }
}