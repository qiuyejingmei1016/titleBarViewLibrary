package com.example.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.UIAction;

/**
 * @describe: 普通titleView 直接按默认加载方式(ImmersionBar.titleBar(R.id.title_bar_view).init())
 *              显示会有问题
 * @author: ASUS
 * @createTime: 2019/4/16 17:08
 * @className: CommonLoadTitleActivity
 */
public class CommonLoadTitleActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        UIAction.setTitle(this, "主页");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.ico_back_white, this);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.ico_set_white, this);

        TextView textView = (TextView) findViewById(R.id.tip);
        textView.setText("加载普通titleView测试\n普通title_view 显示有问题");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.title_bar_view).init();
    }

    @Override
    public void onClick(View v) {
    }
}
