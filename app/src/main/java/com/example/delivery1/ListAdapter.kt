package com.example.delivery1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery1.data.Delivery
import com.example.delivery1.databinding.CustomRowInListDeliveriesBinding


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private lateinit var binding : CustomRowInListDeliveriesBinding
    private var deliveryList = emptyList<Delivery>()


    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_in_list_deliveries,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = deliveryList[position]
        holder.itemView
    }

    override fun getItemCount(): Int {
        return deliveryList.size
    }
}