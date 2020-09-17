package com.example.statustestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

public class Main3Activity extends AppCompatActivity {

    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtils.with(this).init();
        setContentView(R.layout.activity_main3);

        UIAction.setTitle(this, "测试", R.color.white);
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.common_page_back, null);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.common_page_setting, null);

        View titleView = findViewById(R.id.title_view);
        UIAction.setBackgroundResourceSafety(titleView, R.drawable.text_gradient);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(titleView).statusBarDarkFont(true, 0.5f).init();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
            mImmersionBar = null;
        }
    }
}