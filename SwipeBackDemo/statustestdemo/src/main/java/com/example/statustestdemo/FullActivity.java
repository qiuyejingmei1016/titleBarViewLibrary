package com.example.statustestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

public class FullActivity extends AppCompatActivity implements View.OnClickListener {

    private ImmersionBar mImmersionBar;

    private boolean mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);


        UIAction.setTitle(this, "测试", R.color.white);
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.common_page_back, this);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.common_page_setting, this);
        UIAction.setShowTitleDividerLine(this, false);

        View titleView = findViewById(R.id.title_view);
        UIAction.setBackgroundResourceSafety(titleView, R.color.trans);
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.titleBar(titleView).init();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.title_left_img_btn) {
            Intent intent = new Intent(this, Full2Activity.class);
            startActivity(intent);
        } else if (id == R.id.title_right_img_btn) {
            View titleView = findViewById(R.id.title_view);
            if (mSwitch) {
                UIAction.setBackgroundResourceSafety(titleView, R.color.trans);
            } else {
                UIAction.setBackgroundResourceSafety(titleView, R.drawable.title_bg);
            }
            mSwitch = !mSwitch;
        }
    }
}