package com.bubula.notebook.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubula.notebook.R;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by songBaoKang on 2017/7/28.
 */
public class ModePicViewBinder extends ItemViewBinder<ModePic, ModePicViewBinder.ViewHolder> {
    MultiTypeAdapter adapter;
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_mode_pic, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ModePic modePic) {
        adapter = new MultiTypeAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.recyclerView.getContext());
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        /* 注册类型和 View 的对应关系 */
        adapter.register(Pic.class, new PicViewBinder());
        adapter.setItems(modePic.pics);
        ViewGroup.LayoutParams layoutParams = holder.recyclerView.getLayoutParams();
        //高度等于＝条目的高度＋ 10dp的间距 ＋ 10dp（为了让条目居中）
        layoutParams.height = 150*60;
        holder.recyclerView.setLayoutParams(layoutParams);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.recyclerView.getContext(), LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(adapter);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.rv);
        }
    }
}
