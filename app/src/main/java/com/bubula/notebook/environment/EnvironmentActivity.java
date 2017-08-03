package com.bubula.notebook.environment;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bubula.notebook.R;
import com.bubula.notebook.kLog.KLog;

public class EnvironmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);
        sdpath();
    }

    private void sdpath() {
        //获得android data的目录。
        KLog.i("getDataDirectory:"+Environment.getDataDirectory().getAbsolutePath());
        KLog.i("getDownloadCacheDirectory:"+Environment.getDownloadCacheDirectory().getAbsolutePath());
        KLog.i("getExternalStorageDirectory:"+Environment.getExternalStorageDirectory().getAbsolutePath());
        KLog.i("getExternalStorageState:"+Environment.getExternalStorageState().toString());
        KLog.i("getRootDirectory:"+Environment.getRootDirectory().getAbsolutePath());

    }
}
