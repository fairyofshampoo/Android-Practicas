package com.ikariscraft.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.ikariscraft.chat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private List<Chat> messageList;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.chatRecycler.setLayoutManager(new LinearLayoutManager(this));

        messageList = new ArrayList<>();
        messageList.add(new Chat("Hola, ¿cómo estás?", false));
        messageList.add(new Chat("¡Hola! Estoy bien, gracias.", true));

        adapter = new ChatAdapter();
        binding.chatRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }
    private void sendMessage() {
        String messageText = binding.editTextMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {
            messageList.add(new Chat(messageText, false));
            adapter.notifyItemInserted(messageList.size() - 1);
            binding.chatRecycler.scrollToPosition(messageList.size() - 1);
            binding.editTextMessage.setText("");
        }
    }
}