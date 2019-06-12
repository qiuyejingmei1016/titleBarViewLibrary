package com.example.demo.activity;

import android.os.Bundle;
import android.view.View;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.UIAction;

/**
 * @describe: 解决白色状态栏问题
 * @author: ASUS
 * @createTime: 2019/4/16 17:37
 * @className: HandleWhiteStatusBarActivity
 */
public class HandleWhiteStatusBarActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main4;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        UIAction.setTitle(this, "解决白色状态栏问题");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.ico_back_black, this);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.ico_set_black, this);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        //titleView 外部是Toolbar时
//        mImmersionBar.titleBar(R.id.title_bar_view).statusBarDarkFont(true, 0.5f).init();
//        mImmersionBar.fitsSystemWindows(true).statusBarDarkFont(true, 0.5f).init();


        //白色状态栏自定义的titleBar是父布局是RelativeLayout 可直接使用该方式
        mImmersionBar.fitsSystemWindows(true).statusBarDarkFont(true, 0.5f).init();
        //白色状态栏自定义的titleBar是父布局是RelativeLayout适配沉浸也可使用下面方式 定义statusBarColor();
//        mImmersionBar.fitsSystemWindows(true).statusBarDarkFont(true, 0.5f).statusBarColor(R.color.title_bar_white_color).init();
    }
}
