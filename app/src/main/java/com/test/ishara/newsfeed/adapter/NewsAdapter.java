package com.test.ishara.newsfeed.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.ishara.newsfeed.R;
import com.test.ishara.newsfeed.dto.ArticlesDto;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    List<ArticlesDto> articlesDtos;
    private LayoutInflater mInflater;
    Context context;
    private ItemClickListener mClickListener;

    public NewsAdapter(List<ArticlesDto> articlesDtos, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.articlesDtos = articlesDtos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.news_feed_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ArticlesDto animal = articlesDtos.get(i);
        viewHolder.myTextView.setText(animal.getTitle());
    }

    @Override
    public int getItemCount() {
        return articlesDtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.head_line);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    public ArticlesDto getItem(int id) {
        return articlesDtos.get(id);
    }

   public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
