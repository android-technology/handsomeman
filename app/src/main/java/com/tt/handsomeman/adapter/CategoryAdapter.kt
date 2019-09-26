package com.tt.handsomeman.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.tt.handsomeman.R
import com.tt.handsomeman.model.Category

class CategoryAdapter(private val context: Context, private val categoryList: List<Category>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    private var mListener: OnItemClickListener? = null
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = layoutInflater.inflate(R.layout.item_category, parent, false)
        return MyViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = categoryList[position]
        holder.tvCategoryName.text = category.name
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class MyViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        internal var tvCategoryName: TextView
        internal var btnCategoryDetail: ImageButton

        init {

            tvCategoryName = itemView.findViewById(R.id.textViewJobName)
            btnCategoryDetail = itemView.findViewById(R.id.imageButtonCategoryDetail)

            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }

            btnCategoryDetail.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
        }
    }
}