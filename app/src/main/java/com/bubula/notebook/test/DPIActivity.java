package com.bubula.notebook.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bubula.notebook.R;
import com.bubula.notebook.kLog.KLog;

public class DPIActivity extends AppCompatActivity {
    private TextView tv_dpi;
    private Button bt_get_dpi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpi);
        tv_dpi = (TextView) findViewById(R.id.tv_dpi);
        bt_get_dpi= (Button) findViewById(R.id.bt_get_dpi);
        bt_get_dpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDPI();
            }
        });
        getDPI();
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        KLog.i("heigth : " + dm.heightPixels);

        KLog.i("width : " + dm.widthPixels);

// 通过Resources获取

        DisplayMetrics dm2 = getResources().getDisplayMetrics();

        KLog.i("heigth2 : " + dm2.heightPixels);

        KLog.i("width2 : " + dm2.widthPixels);

// 获取屏幕的默认分辨率

        Display display = getWindowManager().getDefaultDisplay();

        KLog.i("width-display :" + display.getWidth());

        KLog.i("heigth-display :" + display.getHeight());
    }

    private void getDPI() {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        tv_dpi.setText("display:" + "\n高：" + height + "\n宽：" +width
                + "\n60DP得像素" +dp2px(DPIActivity.this,60)+"\n屏幕宽度对应得DP"+px2dp(DPIActivity.this,width)
                + "\n屏幕密度比：" +density+"\nDPI"+densityDpi
        );
        KLog.i("display:" + "<>高：" + height + "<>宽：" +width + "<>density：" +density+"<>densityDpi:"+densityDpi);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
