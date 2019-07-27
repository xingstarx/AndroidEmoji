package me.star.emoji.emotion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

public interface IEmoticonTab {
    Drawable obtainTabDrawable(Context context);

    View obtainTabPager(Context context);

    void onTableSelected(int position);
}
