package me.star.emoji.emotion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

public interface IEmoticonTab {
    Drawable obtainTabDrawable(Context context);

    View obtainTabPager(Context context);

    View obtainTabPager(Context context, String userId);

    void onTableSelected(int position);
}
