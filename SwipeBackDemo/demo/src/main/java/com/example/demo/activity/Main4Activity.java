package com.example.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.TestTitleViewActivity;

/**
 * @describe: 解决白色状态栏问题
 * @author: ASUS
 * @createTime: 2019/4/16 17:37
 * @className: Main4Activity
 */
public class Main4Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = (TextView) findViewById(R.id.tip);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.tip) {
            Intent intent = new Intent(this, TestTitleViewActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main4;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        //titleView 外部是Toolbar时
//        mImmersionBar.titleBar(R.id.title_bar_view).statusBarDarkFont(true, 0.5f).init();
//        mImmersionBar.fitsSystemWindows(true).statusBarDarkFont(true, 0.5f).init();

        //titleView 外部是普通view
//        mImmersionBar.titleBar(R.id.title_bar_view).statusBarDarkFont(true, 0.5f).init();
        mImmersionBar.fitsSystemWindows(true).statusBarDarkFont(true, 0.5f).init();
    }
}
