package com.example.physicswallah.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.physicswallah.R
import com.example.physicswallah.model.UsersResponseItem

class TeachersAdapter:RecyclerView.Adapter<TeachersAdapter.TeachersViewHolder>() {
    private val TAG = "TeachersDetailsFragment"


    private val differCallback = object : DiffUtil.ItemCallback<UsersResponseItem>() {
        override fun areItemsTheSame(oldItem: UsersResponseItem, newItem: UsersResponseItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: UsersResponseItem, newItem: UsersResponseItem): Boolean {
            return oldItem === newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    inner class TeachersViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersViewHolder {
        return TeachersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_teachers_recyclerciew_activities, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TeachersViewHolder, position: Int) {
        val guest=differ.currentList[position]
        holder.itemView.apply {
            this.findViewById<TextView>(R.id.tv_teacher_name).text=guest.name
            this.findViewById<TextView>(R.id.btn_view_more).text="View More"
            var qualification =guest.qualification.toString().substring(1, guest.qualification.toString().length - 1)
            var subject=guest.subjects.toString().substring(1, guest.subjects.toString().length-1)
            this.findViewById<TextView>(R.id.tv_type).text=subject+" | "+qualification

            if(!guest.profileImage.isNullOrBlank()){
                var imageView=this.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.iv_teacher)
                Glide.with(context).load(guest.profileImage).into(imageView)
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list: List<UsersResponseItem>) {
        Log.d(TAG, "submitList: "+list)
        differ.submitList(list)
    }
}