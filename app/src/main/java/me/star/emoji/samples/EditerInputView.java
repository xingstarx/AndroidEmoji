package me.star.emoji.samples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.star.emoji.utils.EmojiHelper;

/**
 * Created by xiyouquan on 17-7-15.
 */

public class EditerInputView extends FrameLayout {
    public static final int REQ_CODE_SELECT_FRIEND = 0X0001;

    public interface CommentLister {
//        void onSend(String content, AtInfo atInfo);

        void onAtInput(int reqcode);
    }

//    At at;
    FrameLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    EmojiHelper mEmojiHelper;

    EditText mComment;
    TextView mSend;
    LinearLayout mLLCommentView;
    TextView commentCount;
    View emoji_container;
    boolean canAt = false;

    public void enableAt() {
//        at = new At(mComment, () -> {
//            if (mCommentLister != null) {
//                mCommentLister.onAtInput(REQ_CODE_SELECT_FRIEND);
//            }
//        });
    }


    public void setCommentLister(EditerInputView.CommentLister mCommentLister) {
        this.mCommentLister = mCommentLister;
    }

    private EditerInputView.CommentLister mCommentLister;

    public EditerInputView(Context context) {
        super(context);
        initViews(context);
    }

    public EditerInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public EditerInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_editer_input, this, false);
        addView(view, lp);
        this.mComment = (EditText) view.findViewById(R.id.ed_comment);
        this.emoji_container = view.findViewById(R.id.emoji_container);
        mSend = (TextView) view.findViewById(R.id.btn_send);
        mEmojiHelper = new EmojiHelper(getContext(), view, R.id.ed_comment, R.id.emoji, R.id.emoji_container, R.id.btn_send);
//        mSend.setOnClickListener(v -> {
//            if (((XYBaseActivity) context).checkTestLevel(R.string.dialog_test_level_comment)) {
//                return;
//            }
//            if (mCommentLister != null) {
//                mCommentLister.onSend(mComment.getText().toString(), getAtInfo());
//            }
//        });
    }

    public View getEmoji_container() {
        return emoji_container;
    }

    public void setEmoji_container(View emoji_container) {
        this.emoji_container = emoji_container;
    }

    public void setFocus(boolean isFocus) {
        mComment.setFocusable(isFocus);
    }

    public void setEditerText(String txt) {
        mComment.setText(txt);
    }

    public void setSelection() {

    }

    public void onFinishedSent() {
        mComment.setText("");
        hideInputMethod(mComment);
        mEmojiHelper.hideEmoji();
    }

    public void hide() {
        hideInputMethod(mComment);
    }

    public EmojiHelper getmEmojiHelper() {
        return mEmojiHelper;
    }

    public EditText getCommentEdit() {
        return mComment;
    }

    public LinearLayout getCommentView() {
        return mLLCommentView;
    }

    /**
     * 关闭键盘
     */
    protected void hideInputMethod(View view) {
//        SystemUtils.hideInputMethod(getContext(), view);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


//    private AtInfo getAtInfo() {
//        if (at == null) {
//            return null;
//        }
//        AtInfo atInfo = null;
//        if (at.getAllBean() != null && at.getAllBean().size() > 0) {
//            atInfo = new AtInfo();
//            ArrayList<At.atBean> datas = at.getAllBean();
//            List<String> mentionIds = new ArrayList<>(datas.size());
//            for (At.atBean bean : datas) {
//                mentionIds.add(((EditerInputView.FriendAtBean) bean).userInfo.getObjectId());
//            }
//            atInfo.setMentionList(mentionIds);
//        }
//        return atInfo;
//    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK || requestCode != REQ_CODE_SELECT_FRIEND) {
            return;
        }
//        ArrayList<UserInfo> atFriends = (ArrayList<UserInfo>) data.getSerializableExtra(SelectFriendsActivity.KEY_SELECT_FRIENDS_LIST);
//        ArrayList<At.atBean> result = new ArrayList<>(atFriends.size());
//        for (final UserInfo atFriend : atFriends) {
//            CommentInputView.FriendAtBean bean = new CommentInputView.FriendAtBean(atFriend);
//            result.add(bean);
//        }
//        at.addPositions(result);
    }

//    static class FriendAtBean implements At.atBean {
//        private UserInfo userInfo;
//
//        public FriendAtBean(UserInfo userInfo) {
//            this.userInfo = userInfo;
//        }
//
//        @Override
//        public String showOnEditText() {
//            return userInfo.getNickName();
//        }
//    }
}
