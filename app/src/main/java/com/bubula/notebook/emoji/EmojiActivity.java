package com.bubula.notebook.emoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bubula.notebook.R;
import com.bubula.notebook.kLog.KLog;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmojiActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView, textView2;
    private Button bt1;
    private Button bt2;
    List<Integer> emojis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        textView = (TextView) findViewById(R.id.tv);
        textView2 = (TextView) findViewById(R.id.tv2);
        textView2.setText("\0x1F605\u1F602");
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        EmljiList();
        testU();
    }

    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    private void testU() {
        String s = "U+1F605";
        int unicodeJoy = 0x1F605;
        byte[] arr;
        try {
            arr = s.getBytes("UNICODE");
            KLog.i(Arrays.toString(arr));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
        KLog.i(Character.toChars(unicode));
        builder.append(Character.toChars(unicode));

        return new String(Character.toChars(unicode));
    }

    private String getEmojiStringByUnicode(List<Integer> emojis) {
        StringBuilder builder = new StringBuilder();
        for (Integer integer : emojis) {
            builder.append(Character.toChars(integer));
            KLog.i("codePointAt" + Character.codePointAt("U+1F601", 0));
            KLog.i(Character.toChars(integer));
            KLog.i(builder.append(Character.toChars(integer)));
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
                KLog.i(EmojiUtil2.string2Unicode("\uD83D\uDE01"));
                textView.setText(EmojiUtil2.string2Unicode("\uD83D\uDE01"));
                KLog.i(EmojiUtil2.unicode2String("\uD83D\uDE01"));
                textView2.setText(EmojiUtil2.unicode2String("\uD83D\uDE01"));
                break;
        }
    }

    public String unicode2String2(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            if (hex[i].equals("20")) {
                string.append(" ");
            } else {
                // 转换出每一个代码点
                int data = Integer.parseInt(hex[i], 16);
                // 追加成string
                string.append((char) data);
            }

        }
        return string.toString();
    }
}
