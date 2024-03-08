package com.ikariscraft.chatfalso;

import android.graphics.Color;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ikariscraft.chatfalso.databinding.ChatListItemBinding;

import java.io.Console;

public class ChatAdapter extends ListAdapter<Chat, ChatAdapter.ChatViewHolder> {
    public static final DiffUtil.ItemCallback<Chat> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<Chat>() {
        @Override
        public boolean areItemsTheSame(@NonNull Chat oldMessage, @NonNull Chat newMessage) {
            return oldMessage.equals(newMessage);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Chat oldMessage, @NonNull Chat newMessage) {
            return oldMessage.equals(newMessage);
        }
    };

    protected ChatAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatListItemBinding binding = ChatListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ChatViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = getItem(position);
        holder.bind(chat);
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        private final ChatListItemBinding binding;

        public ChatViewHolder(@NonNull ChatListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Chat chatMessage) {
            binding.botText.setText(chatMessage.getText());
            binding.humanText.setText(chatMessage.getText());
            if (chatMessage.isBot()) {
                binding.botText.setVisibility(View.VISIBLE);
                binding.chatbotIcon.setVisibility(View.VISIBLE);
                binding.humanText.setVisibility(View.INVISIBLE);
                binding.humanIcon.setVisibility(View.INVISIBLE);
            } else {
                binding.humanText.setVisibility(View.VISIBLE);
                binding.humanIcon.setVisibility(View.VISIBLE);
                binding.humanText.setTextColor(Color.BLACK);
                binding.botText.setVisibility(View.INVISIBLE);
                binding.chatbotIcon.setVisibility(View.INVISIBLE);
            }
            binding.executePendingBindings();
        }
    }
}
