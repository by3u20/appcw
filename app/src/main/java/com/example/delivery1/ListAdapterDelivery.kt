package com.example.delivery1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery1.data.Delivery
import com.example.delivery1.databinding.CustomRowInListDeliveriesBinding


class ListAdapterDelivery : RecyclerView.Adapter<ListAdapterDelivery.MyViewHolder>() {

    private lateinit var binding : CustomRowInListDeliveriesBinding
    private var deliveryList = emptyList<Delivery>()


    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_in_list_deliveries,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = deliveryList[position]
        holder.itemView.findViewById<TextView>(R.id.delivery_id_text_list).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.delivery_status_text_list).text = currentItem.status.toString()
        holder.itemView.findViewById<TextView>(R.id.delivery_from__text_list).text = currentItem.from.toString()
        holder.itemView.findViewById<TextView>(R.id.delivery_to__text_list).text = currentItem.to.toString()
        holder.itemView.findViewById<TextView>(R.id.delivery_driver_text_list).text = currentItem.driver.toString()


    }

    fun setData(delivery: List<Delivery>){
        this.deliveryList = delivery
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return deliveryList.size
    }
}