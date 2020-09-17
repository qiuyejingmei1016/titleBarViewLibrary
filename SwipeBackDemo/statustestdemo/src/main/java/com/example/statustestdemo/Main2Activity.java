package com.example.statustestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        StatusBarUtils.with(this).init();

        setContentView(R.layout.activity_main2);

        findViewById(R.id.saveBtn).setOnClickListener(this);

        View titleView = findViewById(R.id.title_view);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(titleView).statusBarDarkFont(true, 0.5f).init();

//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.5f).init();

        UIAction.setTitle(this, "标题", R.color.white);
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.common_page_back, null);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.common_page_setting, null);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
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
