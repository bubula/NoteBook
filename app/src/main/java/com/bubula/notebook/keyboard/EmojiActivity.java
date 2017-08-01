package com.bubula.notebook.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bubula.notebook.R;
import com.bubula.notebook.kLog.KLog;

import java.util.ArrayList;
import java.util.List;

public class EmojiActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button bt1;
    private Button bt2;
    List<Integer> emojis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        textView = (TextView) findViewById(R.id.tv);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        EmljiList();
    }

    private void setEmojiToTextView() {
        int unicodeJoy = 0x1F605;
        String emojiString = getEmojiStringByUnicode(emojis);
        textView.setText(emojiString);
    }

    private void EmljiList() {
        emojis = new ArrayList<>();
        emojis.add(0x2B50);
        emojis.add(0x2B55);
        emojis.add(0x3030);
        emojis.add(0x1F60C);
    }

    private String getEmojiStringByUnicode(int unicode) {
        StringBuilder builder = new StringBuilder();
        builder.append(Character.toChars(unicode));

        return new String(Character.toChars(unicode));
    }

    private String getEmojiStringByUnicode(List<Integer> emojis) {
        StringBuilder builder = new StringBuilder();
        for (Integer integer : emojis) {
            builder.append(Character.toChars(integer));
        }
        return builder.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                setEmojiToTextView();
                break;
            case R.id.bt2:
                KLog.i(textView.getText().toString());
                break;
        }
    }
}
