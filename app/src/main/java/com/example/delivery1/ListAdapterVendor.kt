package com.example.delivery1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery1.data.Delivery


class ListAdapterVendor : RecyclerView.Adapter<ListAdapterVendor.MyViewHolder>() {

    private var deliveryList = emptyList<Delivery>()
    private var reducedDeliveryList = emptyList<Delivery>()

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_site_info,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = reducedDeliveryList[position]
        val vendorName = currentItem.from
        val nDelivery = deliveryList.filter { it.from == vendorName && it.status == "Delivering" }.size

        holder.itemView.findViewById<TextView>(R.id.site_name).text = vendorName
        holder.itemView.findViewById<TextView>(R.id.site_ndeliveries).text = nDelivery.toString()
    }

    fun setData(delivery: List<Delivery>){
        deliveryList = delivery
        reducedDeliveryList = mutableListOf<Delivery>().also { mulDelivery ->
            delivery.forEach { d ->
                if (!mulDelivery.map { it.from }.contains(d.from))
                    mulDelivery.add(d)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reducedDeliveryList.size
    }
}