package com.bubula.notebook.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bubula.notebook.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by songBaoKang on 2017/7/29.
 */

public class ModePicAdapter extends RecyclerView.Adapter<ModePicAdapter.ViewHolder> {
    @Override
    public ModePicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pic, parent, false);
        return new ViewHolder(view);
    }

    private List<Pic> posts = Collections.emptyList();
    public void setPosts(@NonNull List<Pic> posts) {
        this.posts = posts;
    }
    @Override
    public void onBindViewHolder(ModePicAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        ImageView cover;
        @NonNull
        TextView title;


        ViewHolder(View itemView) {
            super(itemView);
            cover= (ImageView) itemView.findViewById(R.id.img);
            title= (TextView) itemView.findViewById(R.id.tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Toast.makeText(v.getContext(), String.valueOf(getAdapterPosition()),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
