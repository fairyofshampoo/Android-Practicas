package com.example.practica6;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica6.databinding.ItemRecordsBinding;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {

    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.equals(newItem);
        }
    };
    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecordsBinding binding = ItemRecordsBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = getItem(position);
        holder.bind(note);

    }

    private OnItemClickListener onItemClickListener;
    interface OnItemClickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        private ItemRecordsBinding binding;

        public NoteViewHolder(@NonNull ItemRecordsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Note note){
            binding.idNote.setText(String.valueOf(note.getId()));
            binding.txtNote.setText(note.getTitle());
            binding.getRoot().setOnClickListener(v->{
                onItemClickListener.onItemClick(note);
            });
            binding.executePendingBindings();
        }
    }
}

