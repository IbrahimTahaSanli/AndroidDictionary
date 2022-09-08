package com.tahasanli.dictionary.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tahasanli.dictionary.R;
import com.tahasanli.dictionary.View.MainActivity;
import com.tahasanli.dictionary.databinding.RecyclerRowBinding;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        RecyclerRowBinding v = RecyclerRowBinding.inflate(inf, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.Word.setText(MainActivity.instance.words.get(position).Word);
        holder.binding.PartOfSpeech.setText(MainActivity.instance.words.get(position).Meanings[0].PartOfSpeech);
        holder.binding.Definition.setText(MainActivity.instance.words.get(position).Meanings[0].definitions[0].Definitions);
        holder.binding.Example.setText(MainActivity.instance.words.get(position).Meanings[0].definitions[0].Example);
    }

    @Override
    public int getItemCount() {
        return MainActivity.instance.words.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding binding;

        public ViewHolder(@NonNull RecyclerRowBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
