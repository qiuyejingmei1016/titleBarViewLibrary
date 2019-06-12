package com.example.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.UIAction;

/**
 * @describe: 带Toolbar的titleView(ImmersionBar.titleBar ( R.id.title_bar_view).init()) 不用指定状态栏颜色
 * @author: ASUS
 * @createTime: 2019/4/16 17:07
 * @className: MainActivity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        UIAction.setTitle(this, "主页");
//        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.ico_back_white, this);
//        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.ico_set_white, this);

        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
        findViewById(R.id.bt6).setOnClickListener(this);
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
        if (viewId == R.id.bt1) {//加载普通titleView测试
            Intent intent = new Intent(this, CommonLoadTitleActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.bt2) {//加载普通titleView测试问题解决
            Intent intent = new Intent(this, HandleCommonLoadTitleActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.bt3) {//ToolBarTitle布局include测试
            Intent intent = new Intent(this, ToolBarTitleIncludeActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.bt4) {//CommonTitle布局include测试
            Intent intent = new Intent(this, CommonTitleIncludeActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.bt5) {//自定义titleBarView aar测试
            Intent intent = new Intent(this, TitleViewAarActivity.class);
            startActivity(intent);
        }else if (viewId == R.id.bt6){//解决白色状态栏问题
            Intent intent = new Intent(this, HandleWhiteStatusBarActivity.class);
            startActivity(intent);
        }
    }
}
