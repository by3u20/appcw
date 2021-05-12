package com.example.delivery1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery1.data.UserEntity


class ListAdapterDriverProfile (val listener: ListAdapterDriverProfile.RowClickListener): RecyclerView.Adapter<ListAdapterDriverProfile.MyViewHolder>() {


    var items  = emptyList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>) {
        this.items = data
    }

    class MyViewHolder (view: View, val listener: ListAdapterDriverProfile.RowClickListener) : RecyclerView.ViewHolder(view){

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvPhone = view.findViewById<TextView>(R.id.tvPhone)
        val tvPassword = view.findViewById<TextView>(R.id.tvPassword)
        val tvRole = view.findViewById<TextView>(R.id.tvRole)
        val deleteUserID = view.findViewById<ImageView>(R.id.deleteUserID)


        fun bind(data: UserEntity) {
            tvRole.text = data.role
            tvName.text = data.username
            tvEmail.text = data.email
            tvPassword.text = data.password
            tvPhone.text = data.phone

            deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterDriverProfile.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = items[position]

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(currentItem)

    }

    fun setData(users: List<UserEntity>){
        val username = "Sam"
        items = users.filter { it.role == "Driver" }
        items = users.filter { it.username == "Sam" }
        notifyDataSetChanged()
    }
    interface RowClickListener{
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}