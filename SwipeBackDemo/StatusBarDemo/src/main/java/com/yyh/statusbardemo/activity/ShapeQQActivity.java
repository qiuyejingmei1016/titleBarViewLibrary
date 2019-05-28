package com.yyh.statusbardemo.activity;

import android.os.Bundle;
import android.view.View;

import com.yyh.statusbardemo.BaseActivity;
import com.yyh.statusbardemo.R;
import com.yyh.statusbardemo.UIAction;

/**
 * @describe: 仿QQ状态栏和标题栏渐变色
 * @author: yyh
 * @createTime: 2018/9/6 10:32
 * @className: ShapeQQActivity
 */
public class ShapeQQActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIAction.setTitle(this, "仿QQ状态栏和标题栏渐变色");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.back_white, this);

        UIAction.setBackgroundResourceSafety(findViewById(R.id.title_bar_view), R.drawable.qq_shape);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shape_qq;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(findViewById(R.id.title_bar_view))
                .navigationBarColor(R.color.tans)
                .init();
    }

    @Override
    public void onClick(View v) {

    }
}