package com.example.demo.activity;

import android.os.Bundle;
import android.view.View;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.UIAction;

/**
 * @describe: 普通titleView 用改方式ImmersionBar.statusBarColor("#ec1d06").init()  可以解决显示问题
 * @author: ASUS
 * @createTime: 2019/4/16 17:09
 * @className: HandleCommonLoadTitleActivity
 */
public class FullActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        UIAction.setTitle(this, "主页");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.ico_back_white, this);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.ico_set_white, this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_full;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        //自定义titlBar的父布局最好是LinearLayout、android.support.v7.widget.Toolbar;当时是RelativeLayout时会有问题,titleBar展示错乱；
        View titleBarView = findViewById(R.id.title_bar_view);
        UIAction.setBackgroundResourceSafety(titleBarView, R.color.trans);
        //以下两种方式相同都可以
//        mImmersionBar.titleBar(titleBarView, true)
//                .transparentBar().keyboardEnable(true)
//                .init();
        mImmersionBar.titleBar(R.id.title_bar_view, false)
                .transparentBar().keyboardEnable(true)
                .init();
    }

    @Override
    public void onClick(View v) {

    }
}
