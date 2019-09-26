package com.tt.handsomeman.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.tt.handsomeman.R

class SpinnerTypePayout(private val context: Context, private val type: Array<String>) : BaseAdapter() {
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return type.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        var view = view
        view = inflater.inflate(R.layout.spinner_signup_payout, null)
        val typeName = view.findViewById<TextView>(R.id.textViewSpinnerPayout)
        typeName.text = type[i]
        return view
    }
}
