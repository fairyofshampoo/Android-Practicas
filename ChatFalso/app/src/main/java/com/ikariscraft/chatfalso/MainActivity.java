package com.ikariscraft.chatfalso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.ikariscraft.chatfalso.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ChatAdapter adapter;
    ArrayList<Chat> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.chatRecycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Chat> responsesList = new ArrayList<>();
        responsesList.add(new Chat("Miau", true));
        responsesList.add(new Chat("No lo creo.", true));
        responsesList.add(new Chat("Tal vez no tienes la respuesta", true));
        responsesList.add(new Chat("Saludos terrícola", true));
        responsesList.add(new Chat("Mwaaaah<3", true));
        responsesList.add(new Chat("Hola", true));
        responsesList.add(new Chat("Bye bye!", true));
        messageList = new ArrayList<>();

        adapter = new ChatAdapter();
        binding.chatRecycler.setAdapter(adapter);
        adapter.submitList(messageList);

        binding.buttonSend.setOnClickListener(v -> {
            String newMessage = binding.editTextMessage.getText().toString();
            if (newMessage.isEmpty()) {
                Toast.makeText(this, "Escribe un mensaje, no dejes vacío el campo", Toast.LENGTH_SHORT).show();
            } else {
                Random random = new Random();
                int randomMessagePosition = random.nextInt(responsesList.size());
                Chat botMessage = responsesList.get(randomMessagePosition);
                Chat humanMessage = new Chat(newMessage, false);
                messageList.add(humanMessage);
                messageList.add(botMessage);
                adapter.notifyItemInserted(messageList.size()-1);
                binding.editTextMessage.getText().clear();
                binding.chatRecycler.scrollToPosition(messageList.size() - 1);
            }
        });
    }
}
