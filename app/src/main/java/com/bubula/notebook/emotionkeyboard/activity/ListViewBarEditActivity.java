package com.bubula.notebook.emotionkeyboard.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bubula.notebook.R;
import com.bubula.notebook.emotionkeyboard.fragment.FaceGroupFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zejian
 * Time  16/1/11 下午4:18
 * Email shinezejian@163.com
 * Description:主体内容为ListView
 */
public class ListViewBarEditActivity extends AppCompatActivity{
    private List<String> itemList;//数据
    private ListView listView;
    private FaceGroupFragment faceGroupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_listview_bar_edit);
        initView();
        initListentener();
        initDatas();
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        listView= (ListView) findViewById(R.id.listview);
    }

    /**
     * 初始化监听器
     */
    public void initListentener(){

    }

    /**
     * 初始化布局数据
     */
    private void initDatas(){
        initEmotionMainFragment();
        initData();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(ListViewBarEditActivity.this,
                android.R.layout.simple_list_item_1, itemList);//适配器
        listView.setAdapter(myAdapter);
    }

    public void initData() {
        itemList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            itemList.add("测试数据" + i);
        }
    }

    /**
     * 初始化表情面板
     */
    public void initEmotionMainFragment(){
        //构建传递参数
        Bundle bundle = new Bundle();
        //绑定主内容编辑框
        bundle.putBoolean(FaceGroupFragment.BIND_TO_EDITTEXT,true);
        //隐藏控件
        bundle.putBoolean(FaceGroupFragment.HIDE_BAR_EDITTEXT_AND_BTN,false);
        //替换fragment
        //创建修改实例
        faceGroupFragment = FaceGroupFragment.newInstance(FaceGroupFragment.class,bundle);
        faceGroupFragment.bindToContentView(listView);
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        // Replace whatever is in thefragment_container view with this fragment,
        // and add the transaction to the backstack
        transaction.replace(R.id.fl_emotionview_main, faceGroupFragment);
        transaction.addToBackStack(null);
        //提交修改
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        /**
         * 判断是否拦截返回键操作
         */
        if (!faceGroupFragment.isInterceptBackPress()) {
            super.onBackPressed();
        }
    }

}