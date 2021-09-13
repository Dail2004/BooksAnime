package com.example.home1.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home1.databinding.ItemBinding;
import com.example.home1.utils.OnItemClick;
import com.example.home1.model.Book;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {

    ItemBinding binding;
    public ArrayList<Book> list = new ArrayList<>();
    OnItemClick onItemClick;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    public void setOnItemClick(OnItemClick itemClick){
        this.onItemClick = itemClick;
    }

    public void  setBooks(ArrayList<Book> animeBooks){
        list = animeBooks;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void onBind(Book animeBooks){
            binding.itemTitle.setText(animeBooks.getTitle());
            binding.animeImage.setImageResource(animeBooks.getImage());
            itemView.setOnClickListener(view -> {
                onItemClick.onItemClick(getAdapterPosition());
            });
        }
    }
}
