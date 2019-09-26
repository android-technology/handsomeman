package com.tt.handsomeman.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.tt.handsomeman.R
import com.tt.handsomeman.viewmodel.MessagesViewModel

class MessagesFragment : Fragment() {

    private var messagesViewModel: MessagesViewModel? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        messagesViewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java!!)
        val root = inflater.inflate(R.layout.fragment_messages, container, false)
        messagesViewModel!!.text.observe(this, Observer { })
        return root
    }
}