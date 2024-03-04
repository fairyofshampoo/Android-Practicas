package com.ikariscraft.recyclernotes;

import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {
    public static final DiffUtil.ItemCallback<Note>


    interface
    public void setOnItemClickListener(AdapterView.OnItemClickListener clic){
        this.onItemClickListener = clic;
    }
}

class NoteViewHolder extends RecyclerView.ViewHolder{

    private ItemRecordsBinding binding;

    public NoteViewHolder(@NonNull ItemRecordsBinding)

}
