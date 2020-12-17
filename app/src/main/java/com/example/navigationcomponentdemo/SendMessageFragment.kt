package com.example.navigationcomponentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 */
class SendMessageFragment : Fragment() {
    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_send_message, container, false)
        setFragmentResultListener("requestKey") {requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            rootView.findViewById<TextView>(R.id.reply_header).visibility = View.VISIBLE
            val replyTextView: TextView = rootView.findViewById(R.id.reply_textview)
            replyTextView.visibility = View.VISIBLE
            replyTextView.text = result
        }
        val sendButton : Button = rootView.findViewById(R.id.send_button)
        val onClickListener: View.OnClickListener = View.OnClickListener { v: View->
            val message: String = rootView.findViewById<EditText>(R.id.message_edittext).text.toString()
            val action = SendMessageFragmentDirections.actionSendMessageFragmentToReplyFragment(message)
            rootView.findNavController().navigate(action)
        }
        sendButton.setOnClickListener(onClickListener)
        return rootView
    }

}
