package com.tt.handsomeman.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.tt.handsomeman.R

class SpinnerCountryPayout(private val context: Context, private val country: Array<String>) : BaseAdapter() {
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return country.size
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
        val countryName = view.findViewById<TextView>(R.id.textViewSpinnerPayout)
        countryName.text = country[i]
        return view
    }
}
