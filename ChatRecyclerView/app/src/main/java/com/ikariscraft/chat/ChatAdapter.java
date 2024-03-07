package com.ikariscraft.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ikariscraft.chat.databinding.ChatListItemBinding;

import java.util.List;

public class ChatAdapter extends ListAdapter<Chat, ChatAdapter.ChatViewHolder> {

    public static final DiffUtil.ItemCallback<Chat> DIFF_CALLBACK = new DiffUtil.ItemCallback<Chat>() {
        @Override
        public boolean areItemsTheSame(@NonNull Chat oldItem, @NonNull Chat newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Chat oldItem, @NonNull Chat newItem) {
            return false;
        }
    };
    protected ChatAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatListItemBinding binding = ChatListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ChatViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        Chat chat = getItem(position);
        holder.bind(chat);
    }
    class ChatViewHolder extends RecyclerView.ViewHolder{

        private final ChatListItemBinding binding;
        public ChatViewHolder(@NonNull ChatListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Chat chatMessage) {
            if (chatMessage.isBot()) {
                binding.botText.setText(chatMessage.getText());
                binding.botText.setVisibility(View.VISIBLE);
                binding.chatbotIcon.setVisibility(View.VISIBLE);
                binding.humanText.setVisibility(View.GONE);
                binding.humanIcon.setVisibility(View.GONE);
            } else {
                binding.humanText.setText(chatMessage.getText());
                binding.humanText.setVisibility(View.VISIBLE);
                binding.humanIcon.setVisibility(View.VISIBLE);
                binding.botText.setVisibility(View.GONE);
                binding.chatbotIcon.setVisibility(View.GONE);
            }
            binding.executePendingBindings();
        }
    }
}