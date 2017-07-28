package com.bubula.notebook.multitype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bubula.notebook.R;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MultiTypeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MultiTypeAdapter adapter;
    Items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multitype);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        adapter = new MultiTypeAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        /* 注册类型和 View 的对应关系 */
        adapter.register(Pic.class, new PicViewBinder());
        adapter.register(ModePic.class, new ModePicViewBinder());
        recyclerView.setAdapter(adapter);

        /* 模拟加载数据，也可以稍后再加载，然后使用
         * adapter.notifyDataSetChanged() 刷新列表 */
        List<Pic> pics=new ArrayList<>();
        pics.add(new Pic("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg", "1"));
        pics.add(new Pic("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1738728778,7298606&fm=117&gp=0.jpg", "2"));
        pics.add(new Pic("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3144465310,4114570573&fm=117&gp=0.jpg", "3"));
        pics.add(new Pic("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1694537514,3482815026&fm=200&gp=0.jpg", "4"));
        pics.add(new Pic("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2183314203,562241301&fm=117&gp=0.jpg", "5"));
        pics.add(new Pic("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3965705221,2010595691&fm=117&gp=0.jpg", "6"));

        items = new Items();
        items.add(new ModePic(pics));
            items.add(new Pic("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg", "1"));
            items.add(new Pic("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1738728778,7298606&fm=117&gp=0.jpg", "2"));
            items.add(new Pic("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3144465310,4114570573&fm=117&gp=0.jpg", "3"));
            items.add(new Pic("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1694537514,3482815026&fm=200&gp=0.jpg", "4"));
            items.add(new Pic("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2183314203,562241301&fm=117&gp=0.jpg", "5"));
            items.add(new Pic("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3965705221,2010595691&fm=117&gp=0.jpg", "6"));

        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
