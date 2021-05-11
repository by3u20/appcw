package com.example.delivery1.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery1.R
import com.example.delivery1.data.UserEntity
import com.example.delivery1.databinding.RecyclerviewRowBinding

class UserRecylerViewAdapter (val listener: RowClickListener): RecyclerView.Adapter<UserRecylerViewAdapter.MyViewHolder>() {

    private lateinit var binding: RecyclerviewRowBinding
    var items  = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecylerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserRecylerViewAdapter.MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])

    }



    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view) {

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvPhone = view.findViewById<TextView>(R.id.tvPhone)
        val tvPassword = view.findViewById<TextView>(R.id.tvPassword)
        val deleteUserID = view.findViewById<ImageView>(R.id.deleteUserID)

        fun bind(data: UserEntity) {
            tvName.text = data.username
            tvEmail.text = data.email
            tvPassword.text = data.password
            tvPhone.text = data.phone

            deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
        }
    }

    interface RowClickListener{
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }
}