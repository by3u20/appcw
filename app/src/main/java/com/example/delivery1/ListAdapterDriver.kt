package com.example.delivery1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery1.data.Delivery
import com.example.delivery1.data.UserEntity


class ListAdapterDriver : RecyclerView.Adapter<ListAdapterDriver.MyViewHolder>() {

    private var driverList = emptyList<UserEntity>()

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_info,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = driverList[position]

        holder.itemView.findViewById<TextView>(R.id.site_name).text = currentItem.username
        holder.itemView.findViewById<TextView>(R.id.site_ndeliveries).visibility = View.GONE
        holder.itemView.findViewById<TextView>(R.id.site_ndeliveries_title).visibility = View.GONE
    }

    fun setData(users: List<UserEntity>){
        driverList = users.filter { it.role == "Driver" }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return driverList.size
    }
}