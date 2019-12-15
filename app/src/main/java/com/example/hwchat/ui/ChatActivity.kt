package com.example.hwchat.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hwchat.R
import com.example.hwchat.adapter.MessageAdapter
import com.example.hwchat.data.PREFERENCES
import com.example.hwchat.data.USERNAME
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    private var viewModel: ChatViewModel = ChatViewModel(this)
    private var adapter: MessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initRecycler()
        btn_send_message.setOnClickListener {
            viewModel.sendMessage(et_message.text.toString())
            et_message.setText("")
        }
        viewModel.getMessagesLiveData.observe(this, Observer {
            if (it.data != null){
                adapter?.updateData(it.data.toMutableList())
            }
        })
        viewModel.messageSendLiveData.observe(this, Observer {
            if (it.data != null) {
                Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error ${it.error?.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecycler() {
        val username: String = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            .getString(USERNAME, "").toString()

        adapter = MessageAdapter(username, arrayListOf())
        adapter?.setHasStableIds(true)
        rv_messages.adapter = adapter
    }
}
