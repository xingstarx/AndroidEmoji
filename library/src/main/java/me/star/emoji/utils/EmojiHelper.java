package me.star.emoji.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.star.emoji.R;
import me.star.emoji.emotion.EmojiTab;
import me.star.emoji.emotion.IEmojiItemClickListener;

public class EmojiHelper implements TextWatcher {
    private static final String TAG = "EmojiHelper";
    private EditText mEdit;
    private ImageView mEmoji;
    private ImageView record;
    private View customRecord;
    private ViewGroup mEmojiContainer;
    private boolean isShowInput = true;
    private EmojiTab mEmojiPager;
    private Context context;
    private TextView mBtnSend;
    private LinearLayout mLlComment;
    private View child;
    private int start;
    private int count;
    private String mUserId;

    public void setUserId(String userId) {
        this.mUserId = userId;
    }

    public EmojiHelper(Context context, View view, int editRes, int imgRes, int emojiContainer) {
        this.context = context;
        this.mEdit = (EditText) view.findViewById(editRes);
        this.mEmoji = (ImageView) view.findViewById(imgRes);
        mEmojiContainer = (ViewGroup) view.findViewById(emojiContainer);
        hideEmoji();
        initEvent();
    }


    public EmojiHelper(Context context, View view, int editRes, int imgRes, int emojiContainer, int btnRes) {
        this(context, view, editRes, imgRes, emojiContainer);
        this.mBtnSend = (TextView) view.findViewById(btnRes);
    }

    public ImageView getRecord() {
        return record;
    }

    public boolean isShowInput() {
        return isShowInput;
    }

    public void setShowInput(boolean showInput) {
        isShowInput = showInput;
    }

    //抽取公用的方法
    private void initEvent() {
        mEdit.addTextChangedListener(this);
        mEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hideEmoji();
                }
            }
        });
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideEmoji();

            }
        });

        mEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) EmojiHelper.this.context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (!isShowInput) {
                    hideEmoji();
                    imm.showSoftInput(mEdit, InputMethodManager.SHOW_FORCED);
                } else {
                    mEmoji.setImageResource(R.drawable.ic_keyboard);
                    imm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
                    isShowInput = false;
                    mEmojiContainer.setVisibility(View.VISIBLE);
                    if (customRecord != null) {
                        customRecord.setVisibility(View.GONE);
                    }
                    if (child != null) {
                        child.setVisibility(View.VISIBLE);
                    }
                    if (mEmojiPager != null) {
                        return;
                    }
                    mEmojiPager = new EmojiTab();
                    child = mEmojiPager.obtainTabPager(EmojiHelper.this.context, mUserId);
                    mEmojiContainer.addView(child);
                    mEmojiPager.setOnItemClickListener(new IEmojiItemClickListener() {
                        @Override
                        public void onEmojiClick(String s) {
                            int start = mEdit.getSelectionStart();
                            mEdit.getText().insert(start, s);
                        }

                        @Override
                        public void onDeleteClick() {
                            mEdit.dispatchKeyEvent(new KeyEvent(0, 67));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.start = start;
        this.count = count;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() <= this.start) {
            return;
        }
        if (AndroidEmoji.isEmoji(s.subSequence(this.start, this.start + this.count).toString())) {
            mEdit.removeTextChangedListener(this);
            mEdit.setText(AndroidEmoji.ensure(s.toString()), TextView.BufferType.SPANNABLE);
            mEdit.addTextChangedListener(this);
            mEdit.setSelection(this.start + this.count);
        }
    }

    public void hideEmoji() {
        mEmoji.setImageResource(R.drawable.ic_emoji);
        mEmojiContainer.setVisibility(View.GONE);
        isShowInput = true;
    }
}
