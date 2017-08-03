package com.bubula.notebook.key;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bubula.notebook.R;
import com.bubula.notebook.kLog.KLog;


/**
 * 聊天键盘
 *
 * @author KANG
 * @用途
 * @time 2015-9-23 上午11:15:12
 */
public class KeyLayout extends LinearLayout implements OnClickListener {
    protected static final String TAG = "KeyLayout";
    private Context mcontext;
    private Activity activity;
    private InputMethodManager manager;
    private LinearLayout moreView;
    private FrameLayout face_fl;// 表情
//    private RelativeLayout rlKeyboard;// 文字输入区域
    private ImageButton ivPic;// 显示更多功能区域
    private ImageButton ivEmoji;// 显示表情键盘
    private ImageButton ivBoard;// 显示输入键盘
    private int keyboardHeight;// 键盘高度835/598/582
    private EditText editContent;//文字输入框
    private boolean keyBoardShow = false;
    private boolean faecBoardShow = false;
    private boolean moreBoardShow = false;

    @SuppressLint("InflateParams")
    private void initView(Context context) {
        mcontext = context;
        activity = (Activity) context;
        moreView = (LinearLayout) LayoutInflater.from(mcontext).inflate(
                R.layout.view_key, null);
        addView(moreView);
        keyboardHeight = 759;
//				WzlpPreferences.getInstance(context)
//				.getKeyboardHeight();

        moreView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        initKeyView();// 按键区域view初始化
        initFaceView();// 功能区域view初始化
        manager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void initFaceView() {
        face_fl = (FrameLayout) moreView.findViewById(R.id.face_fl);
        setKeyView();
    }


    private void initKeyView() {
        ivEmoji = (ImageButton) moreView.findViewById(R.id.iv_key_emoji);
        ivBoard = (ImageButton) moreView.findViewById(R.id.iv_key_board);
        ivPic = (ImageButton) moreView.findViewById(R.id.iv_key_pic);
        editContent = (EditText) moreView.findViewById(R.id.edt_key_content);
        editContent.setFocusable(true);
        editContent.setFocusableInTouchMode(true);
        editContent.requestFocus();
//        rlKeyboard = (RelativeLayout) moreView
//                .findViewById(R.id.rl_key_keyboard);
        editContent.addTextChangedListener(textWatcher);
        editContent.setOnFocusChangeListener(focusChangeListener);
        checkKeyboardHeight();
        editContent.setOnClickListener(this);
        ivEmoji.setOnClickListener(this);
        ivBoard.setOnClickListener(this);
        ivPic.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_key_emoji:// 显示表情
                // 键盘
                //showKeyBoardRl();
                // 隐藏更多
                //hideMore();
                showEmoji();
                break;
            case R.id.iv_key_board:// 显示键盘
                hideFace();
                break;
            case R.id.iv_key_pic:
                Toast.makeText(mcontext,"选择图片去",Toast.LENGTH_SHORT).show();
                break;

            case R.id.edt_key_content:// 文字输入框
                Log.i(TAG, " 文字输入框");
                if (face_fl.getVisibility() == GONE) {
                    face_fl.setVisibility(VISIBLE);
                }
                break;

            default:
                break;
        }

    }


    /**
     * 显示输入框布局
     */
    private void showKeyBoardRl() {
        if (!keyBoardShow) {
            showKeyboard();
        }
    }



    /**
     * 显示表情键盘
     */
    private void showEmoji() {
        Log.i(TAG, "显示表情键盘");
        faecBoardShow = true;
        ivEmoji.setVisibility(INVISIBLE);
        ivBoard.setVisibility(VISIBLE);
        if (!face_fl.isShown()) {
            face_fl.setVisibility(VISIBLE);
        }
        if (keyBoardShow) {
            hideKeyboard();
        }
    }

    /**
     * 隐藏表情键盘
     */
    private void hideFace() {
        Log.i(TAG, "隐藏表情键盘");
        faecBoardShow = false;
        ivEmoji.setVisibility(VISIBLE);
        ivBoard.setVisibility(INVISIBLE);
        showKeyboard();
    }

    /**
     * 键盘弹出
     */
    private void keyUp() {
        if (!keyBoardShow) {
            Log.i(TAG, "键盘弹出");
            keyBoardShow = true;
            faecBoardShow = false;
            moreBoardShow = false;
            ivEmoji.setVisibility(VISIBLE);
            ivBoard.setVisibility(INVISIBLE);
        }

    }

    /**
     * 键盘收回
     */
    private void keDown() {
        if (keyBoardShow) {
            keyBoardShow = false;
            Log.i(TAG, "键盘回收");
            Log.i(TAG, "faecBoardShow" + faecBoardShow);
            Log.i(TAG, "moreBoardShow" + moreBoardShow);
            if (faecBoardShow || moreBoardShow) {
                return;
            } else {
                face_fl.setVisibility(GONE);
            }

        }

    }

    /**
     * 输入框文字监听（因此显示发送按钮）
     */
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void afterTextChanged(Editable arg0) {

        }
    };

    public void emptyKeyEdit() {
        if (editContent != null) {
            editContent.setText("");
        }
    }

    /**
     * 返回输入框
     *
     * @return
     */
    public EditText getKeyEditText() {
        return editContent;
    }
//SPUtils.put(paramActivity,"KeyboardHeight", height);
//    height = (int) SPUtils.get(paramActivity,"KeyboardHeight",787);//787为默认软键盘高度 基本差不离
//
    /**
     * 返回输入框输入内容
     *
     * @return
     */
    public String getEditText() {
        if (editContent != null) {
            return editContent.getText().toString();
        }
        return null;
    }

    /**
     * 修改输入框背景色
     */
    private OnFocusChangeListener focusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {

            }
        }
    };

    private void checkKeyboardHeight() {
        getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        getWindowVisibleDisplayFrame(r);
                        int screenHeight = getRootView().getHeight();
                        int heightDifference = screenHeight - (r.bottom);
                        Log.i(TAG, "heightDifference==>" + heightDifference);
                        if (heightDifference > 100) {
                            savekKeyboardHeight(heightDifference);
                            keyUp();
                        } else {
                            keDown();
                        }
                    }

                });
    }

    private void savekKeyboardHeight(int height) {
        if (height > 100 && height != keyboardHeight) {
            Log.i(TAG, "将键盘高度保持到本地");
            keyboardHeight = height;
//			WzlpPreferences.getInstance(mcontext).saveKeyboardHeight(
//					keyboardHeight);

        }

    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(activity.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏所有
     */
    public void hideAll() {
        KLog.i(TAG, "keyBoardShow" + keyBoardShow);
        KLog.i(TAG, "faecBoardShow" + faecBoardShow);
        KLog.i(TAG, "moreBoardShow" + moreBoardShow);

        hideKeyboard();
        ivEmoji.setVisibility(VISIBLE);
        ivBoard.setVisibility(INVISIBLE);
        face_fl.setVisibility(GONE);
    }

    private void showKeyboard() {
        if (face_fl.getVisibility() == GONE) {
            face_fl.setVisibility(VISIBLE);
        }
        editContent.setFocusable(true);
        editContent.setFocusableInTouchMode(true);
        editContent.requestFocus();
        InputMethodManager imm = (InputMethodManager) editContent.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editContent, 0);
    }

    /**
     * 设置表情区域的高度
     */
    private void setKeyView() {
        if (keyboardHeight > 100) {
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                    keyboardHeight);
            face_fl.setLayoutParams(params);
        }
    }

    public KeyLayout(Context context) {
        super(context);
        initView(context);
    }

    public KeyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public KeyLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

}
