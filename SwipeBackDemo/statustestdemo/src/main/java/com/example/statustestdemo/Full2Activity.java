package com.example.statustestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

public class Full2Activity extends AppCompatActivity implements View.OnClickListener {

    private ImmersionBar mImmersionBar;
    private boolean mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full2);

        UIAction.setTitle(this, "测试", R.color.white);
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.common_page_back, null);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.common_page_setting, this);
        UIAction.setShowTitleDividerLine(this, false);

        View titleView = findViewById(R.id.title_view);
        UIAction.setBackgroundResourceSafety(titleView, R.color.trans);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(titleView).init();
    }

    @Override
    public void onClick(View v) {
        if (mSwitch) {
            View titleView = findViewById(R.id.title_view);
            UIAction.setBackgroundResourceSafety(titleView, R.color.trans);
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.titleBar(titleView).statusBarDarkFont(false).init();
        } else {
            View titleView = findViewById(R.id.title_view);
            UIAction.setBackgroundResourceSafety(titleView, R.color.white);
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.titleBar(titleView).statusBarDarkFont(true, 0.5f).init();
        }
        mSwitch = !mSwitch;
    }
}