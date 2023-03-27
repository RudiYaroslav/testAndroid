package com.example.testaiwe.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.testaiwe.R
import com.example.testaiwe.databinding.ItemLayoutBinding
import com.example.testaiwe.model.MarketCapPercentage

class ItemAdapter:ListAdapter<MarketCapPercentage, ItemAdapter.ItemHolder>(callback) {

    class ItemHolder(private val binding: ItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun bind(percentage: MarketCapPercentage){
                binding.textViewName.text = binding.root.context.getString(R.string.name,percentage.name)
                binding.textViewPerc.text = binding.root.context.getString(R.string.perc, percentage.percentage)

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object{
      val callback = object :DiffUtil.ItemCallback<MarketCapPercentage>(){
          override fun areItemsTheSame(
              oldItem: MarketCapPercentage,
              newItem: MarketCapPercentage
          ): Boolean {
              return oldItem.name == newItem.name
          }

          override fun areContentsTheSame(
              oldItem: MarketCapPercentage,
              newItem: MarketCapPercentage
          ): Boolean {
              return oldItem.percentage == newItem.percentage
          }

      }
    }
}