package com.nkslearning.questions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<Object> data ;

    public MyAdapter(ArrayList<Object> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1) return new HeaderHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row_for_header,parent,false));
        else return new ItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row_for_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object dataItem = data.get(position);
        if (holder instanceof HeaderHolder && dataItem instanceof String){
            HeaderHolder headerHolder = (HeaderHolder) holder;
            headerHolder.header.setText((String)data.get(position));
        }else if (holder instanceof ItemHolder && dataItem instanceof DataItem){
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.title.setText(((DataItem) dataItem).title);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        // 1 for header and 2 for items
        return data.get(position) instanceof String?1:2;
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder{
        private final TextView header;
        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
        }
    }
    public static class ItemHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
