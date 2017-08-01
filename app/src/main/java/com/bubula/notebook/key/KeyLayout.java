package com.bubula.notebook.key;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bubula.notebook.R;
import com.bubula.notebook.kLog.KLog;


/**
 * 聊天键盘
 * 
 * @用途
 * @author KANG
 * @time 2015-9-23 上午11:15:12
 * 
 */
public class KeyLayout extends LinearLayout implements OnClickListener {
	protected static final String TAG = "KeyLayout";
	private Context mcontext;
	private Activity activity;
	private InputMethodManager manager;
	private LinearLayout moreView;
	private FrameLayout face_fl;// 表情
	private ImageButton key_show_voice, key_keyboard;
	private RelativeLayout key_keyboard_rl;// 文字输入区域
	private ImageButton key_more_show;// 显示更多功能区域
	private ImageButton key_more_hide;// 隐藏更多功能区域
	private ImageButton key_face_show;// 显示表情键盘
	private ImageButton key_face_hide;// 隐藏表情键盘
	private Button key_record_voice;// 录音按钮
	private Button key_send;// 发送按钮
	private int keyboardHeight;// 键盘高度835/598/582
	private EditText key_editText;
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
		key_show_voice = (ImageButton) moreView
				.findViewById(R.id.key_show_voice);
		key_keyboard = (ImageButton) moreView.findViewById(R.id.key_keyboard);
		key_face_show = (ImageButton) moreView.findViewById(R.id.key_face_show);
		key_face_hide = (ImageButton) moreView.findViewById(R.id.key_face_hide);
		key_more_show = (ImageButton) moreView.findViewById(R.id.key_more_show);
		key_more_hide = (ImageButton) moreView.findViewById(R.id.key_more_hide);
		key_editText = (EditText) moreView.findViewById(R.id.key_editText);
		key_send = (Button) moreView.findViewById(R.id.key_send);
		key_editText.setFocusable(true);
		key_editText.setFocusableInTouchMode(true);
		key_editText.requestFocus();
		key_keyboard_rl = (RelativeLayout) moreView
				.findViewById(R.id.key_keyboard_rl);
		key_record_voice = (Button) moreView
				.findViewById(R.id.key_record_voice);
		key_editText.addTextChangedListener(textWatcher);
		key_editText.setOnFocusChangeListener(focusChangeListener);
		checkKeyboardHeight();
		key_editText.setOnClickListener(this);
		key_show_voice.setOnClickListener(this);
		key_keyboard.setOnClickListener(this);
		key_face_show.setOnClickListener(this);
		key_face_hide.setOnClickListener(this);
		key_more_show.setOnClickListener(this);
		key_more_hide.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.key_show_voice:// 现实音频
			showRecordVoice();
			break;
		case R.id.key_keyboard:// 键盘
			showKeyBoardRl();
			break;
		case R.id.key_face_show:// 显示表情
			showFace();
			break;
		case R.id.key_face_hide:// 隐藏表情
			hideFace();
			break;
		case R.id.key_more_show:// 显示更多
			showMore();
			break;
		case R.id.key_more_hide:// 隐藏更多
			hideMore();
			break;

		case R.id.key_editText:// 文字输入框
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
	 * 显示录入音频布局
	 */
	private void showRecordVoice() {
		if (keyBoardShow) {
			hideKeyboard();
		}
		if (face_fl.isShown()) {
			face_fl.setVisibility(GONE);
			key_face_show.setVisibility(VISIBLE);
			key_face_hide.setVisibility(INVISIBLE);
		}
		key_keyboard_rl.setVisibility(INVISIBLE);
		key_record_voice.setVisibility(VISIBLE);
		key_keyboard.setVisibility(VISIBLE);
		key_show_voice.setVisibility(INVISIBLE);

	}

	/**
	 * 显示输入框布局
	 */
	private void showKeyBoardRl() {
		if (!keyBoardShow) {
			showKeyboard();
		}
		key_record_voice.setVisibility(INVISIBLE);
		key_keyboard_rl.setVisibility(VISIBLE);
		key_show_voice.setVisibility(VISIBLE);
		key_keyboard.setVisibility(INVISIBLE);
	}

	/**
	 * 显示更多功能页面
	 */
	private void showMore() {
		Log.i(TAG, "显示更多功能页面");
		moreBoardShow = true;
		key_more_show.setVisibility(INVISIBLE);
		key_more_hide.setVisibility(VISIBLE);
		key_face_show.setVisibility(VISIBLE);
		key_face_hide.setVisibility(INVISIBLE);
		if (!face_fl.isShown()) {
			face_fl.setVisibility(VISIBLE);
		}
		if (keyBoardShow) {
			hideKeyboard();
		}
	}

	/**
	 * 隐藏更多页面
	 */
	private void hideMore() {
		Log.i(TAG, "隐藏更多页面");
		moreBoardShow = false;
		key_more_show.setVisibility(VISIBLE);
		key_more_hide.setVisibility(INVISIBLE);
		if (key_record_voice.getVisibility() == VISIBLE) {
			face_fl.setVisibility(GONE);
		} else {
			showKeyboard();
		}

	}

	/**
	 * 显示表情键盘
	 */
	private void showFace() {
		Log.i(TAG, "显示表情键盘");
		faecBoardShow = true;
		key_face_show.setVisibility(INVISIBLE);
		key_face_hide.setVisibility(VISIBLE);
		key_more_show.setVisibility(VISIBLE);
		key_more_hide.setVisibility(INVISIBLE);
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
		key_face_show.setVisibility(VISIBLE);
		key_face_hide.setVisibility(INVISIBLE);
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
			key_face_show.setVisibility(VISIBLE);
			key_face_hide.setVisibility(INVISIBLE);
			if (key_editText.getText().toString().length() == 0) {
				key_more_show.setVisibility(VISIBLE);
			}
			key_more_hide.setVisibility(INVISIBLE);
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
			if (TextUtils.isEmpty(s)) {
				key_send.setVisibility(View.INVISIBLE);
				key_more_show.setVisibility(View.VISIBLE);
			} else {
				key_send.setVisibility(View.VISIBLE);
				key_more_show.setVisibility(INVISIBLE);
			}

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
		if (key_editText != null) {
			key_editText.setText("");
		}
	}

	/**
	 * 返回输入框
	 * 
	 * @return
	 */
	public EditText getKeyEditText() {
		return key_editText;
	}

	/**
	 * 返回输入框输入内容
	 * 
	 * @return
	 */
	public String getEditText() {
		if (key_editText != null) {
			return key_editText.getText().toString();
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
		key_face_show.setVisibility(VISIBLE);
		key_face_hide.setVisibility(INVISIBLE);
		key_more_show.setVisibility(VISIBLE);
		key_more_hide.setVisibility(INVISIBLE);
		face_fl.setVisibility(GONE);
	}

	private void showKeyboard() {
		if (face_fl.getVisibility() == GONE) {
			face_fl.setVisibility(VISIBLE);
		}
		key_editText.setFocusable(true);
		key_editText.setFocusableInTouchMode(true);
		key_editText.requestFocus();
		InputMethodManager imm = (InputMethodManager) key_editText.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(key_editText, 0);
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
