package com.example.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.UIAction;

/**
 *  @describe: 带Toolbar的titleView(ImmersionBar.titleBar(R.id.title_bar_view).init()) 不用指定状态栏颜色
 *  @author: ASUS
 *  @createTime: 2019/4/16 17:07
 *  @className:  MainActivity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        UIAction.setTitle(this, "主页");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.ico_back, this);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.ico_set, this);

        findViewById(R.id.tip).setOnClickListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.title_bar_view).init();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.tip) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
    }
}
