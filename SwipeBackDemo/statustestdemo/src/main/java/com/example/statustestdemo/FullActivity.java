package com.example.statustestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

public class FullActivity extends AppCompatActivity {

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);


        UIAction.setTitle(this, "测试", R.color.white);
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.common_page_back, null);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.common_page_setting, null);
        UIAction.setShowTitleDividerLine(this,false);

        View titleView = findViewById(R.id.title_view);
        UIAction.setBackgroundResourceSafety(titleView, R.color.trans);
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.titleBar(titleView).init();
        mImmersionBar.titleBar(titleView).statusBarDarkFont(true, 0.5f).init();//
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
