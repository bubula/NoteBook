package com.bubula.notebook.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubula.notebook.R;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by songBaoKang on 2017/7/28.
 */
public class ModePicViewBinder extends ItemViewBinder<ModePic, ModePicViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_mode_pic, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ModePic modePic) {
        holder.setPosts(modePic.pics);
        assertGetAdapterNonNull();
    }
    /**
     * Just test
     */
    private void assertGetAdapterNonNull() {
        if (getAdapter() == null) {
            throw new NullPointerException("getAdapter() == null");
        }
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        ModePicAdapter modePicAdapter;
        ViewHolder(View itemView) {
            super(itemView);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.rv);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            modePicAdapter = new ModePicAdapter();
            recyclerView.setAdapter(modePicAdapter);
        }
        private void setPosts(List<Pic> posts) {
            modePicAdapter.setPosts(posts);
            modePicAdapter.notifyDataSetChanged();
        }
    }
}
