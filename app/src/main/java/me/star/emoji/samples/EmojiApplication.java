package me.star.emoji.samples;

import android.app.Application;

import me.star.emoji.utils.AndroidEmoji;

public class EmojiApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidEmoji.init(this);
    }
}
