package com.tt.handsomeman.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.tt.handsomeman.R
import com.tt.handsomeman.model.Job

class JobAdapter(private val context: Context, private val jobList: List<Job>) : RecyclerView.Adapter<JobAdapter.MyViewHolder>() {

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
        val item = layoutInflater.inflate(R.layout.item_job, parent, false)
        return MyViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val job = jobList[position]

        holder.tvJobTitle.text = job.title
        holder.tvCreateTime.text = job.setCreateTimeBinding(job.createTime)
        holder.tvBudget.text = job.setBudgetRange(job.budgetMin, job.budgetMax)
        holder.tvLocationName.text = job.setLocationBinding(job.location!!)
        holder.tvDeadline.text = job.setDeadlineBinding(job.deadline)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    internal inner class MyViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        var tvJobTitle: TextView
        var tvCreateTime: TextView
        var tvBudget: TextView
        var tvLocationName: TextView
        var tvDeadline: TextView
        var btnJobDetail: ImageButton

        init {

            tvJobTitle = itemView.findViewById(R.id.textViewJobName)
            tvCreateTime = itemView.findViewById(R.id.textViewCreateTime)
            tvBudget = itemView.findViewById(R.id.textViewBudgetRange)
            tvLocationName = itemView.findViewById(R.id.textViewLocationName)
            tvDeadline = itemView.findViewById(R.id.textViewDeadLine)

            btnJobDetail = itemView.findViewById(R.id.imageButtonJobDetail)

            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }

            btnJobDetail.setOnClickListener {
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