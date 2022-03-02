package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Myadapter  extends RecyclerView.Adapter<com.example.myapplication.Myadapter.ViewHolder> {
        ArrayList<Book> listdata;
        private BookListener listener;
        private Context context;
        //private final OnClickListener mOnClickListener = new MyOnClickListener();

        // RecyclerView recyclerView;
        public Myadapter(Context context, ArrayList<Book> listdata,BookListener listener)  {
            this.context = context;
            this.listdata = listdata;
            this.listener=listener;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.book_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder( ViewHolder holder, int position) {
            final Book book = listdata.get(position);
            holder.textView.setText(book.getNomLivre());
        Glide.with(context)
                .load(book.getImgCouverture())
                .into(holder.imageView);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(book);
                }
            });
        }


        @Override
        public int getItemCount() {
            return listdata.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                this.imageView = (ImageView) itemView.findViewById(R.id.book_img);
                this.textView = (TextView) itemView.findViewById(R.id.book_title);
            }
        }

        public interface BookListener {
            public void onItemClicked(Book book);
        }

    }
