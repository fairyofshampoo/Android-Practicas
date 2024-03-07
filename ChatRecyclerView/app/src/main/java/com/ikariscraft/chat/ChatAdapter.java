package com.ikariscraft.chat;

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
        public boolean areItemsTheSame(@NonNull Chat oldMessage, @NonNull Chat newMessage) {
            return oldMessage.getId().equals(newMessage.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Chat oldMessage, @NonNull Chat newMessage) {
            return oldMessage.equals(newMessage);
        }
    };


    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {

    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Chat chatMessage);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected ChatAdapter() {
        super(DIFF_CALLBACK);
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        private final ChatListItemBinding binding;

        public ChatViewHolder(@NonNull ChatListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Chat chatMessage) {
            binding.executePendingBindings();
        }
    }
}