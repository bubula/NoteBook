package com.bubula.notebook.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bubula.notebook.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by songBaoKang on 2017/7/28.
 */
public class PicViewBinder extends ItemViewBinder<Pic, PicViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_pic, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Pic pic) {
        Glide.with(holder.img.getContext())
                .load(pic.url).centerCrop().crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.img);
        holder.textView.setText(pic.num);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView img;
        private TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
            textView= (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
