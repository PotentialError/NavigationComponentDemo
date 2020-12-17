package com.example.navigationcomponentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 */
class ReplyFragment : Fragment() {
    lateinit var rootView: View;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_reply, container, false)

        val args = ReplyFragmentArgs.fromBundle(requireArguments())
        rootView.findViewById<TextView>(R.id.message_textview).text = args.messageArg
        val sendReplyButton : Button = rootView.findViewById(R.id.send_reply_button)
        val onClickListener: View.OnClickListener = View.OnClickListener { v: View->
            val reply: String = rootView.findViewById<EditText>(R.id.reply_edittext).text.toString()
            setFragmentResult("requestKey", bundleOf("bundleKey" to reply))
            val action = ReplyFragmentDirections.actionReplyFragmentToSendMessageFragment()
            rootView.findNavController().navigate(action)
        }
        sendReplyButton.setOnClickListener(onClickListener)
        return rootView
    }

}
