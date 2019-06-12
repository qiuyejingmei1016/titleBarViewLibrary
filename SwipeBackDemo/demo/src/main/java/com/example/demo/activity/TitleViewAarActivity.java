package com.example.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bairuitech.anychat.titlebar.OnTitleBarListener;
import com.bairuitech.anychat.titlebar.TitleBar;
import com.example.demo.BaseActivity;
import com.example.demo.R;

/**
 * @describe: 自定义titleBarView aar
 * @author: yyh
 * @createTime: 2019/5/28 9:58
 * @className: TitleViewAarActivity
 */
public class TitleViewAarActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test_title_view;
    }

    private void initView() {
        TitleBar titleBar = (TitleBar) findViewById(R.id.title_view);
        //设置标题内容
        titleBar.setTitle("测试");

        //设置左边展示内容
//        titleBar.setLeftTitle("返回");
        titleBar.setLeftIcon(getResources().getDrawable(R.mipmap.bar_icon_back_white));

        //设置右边展示内容
//        titleBar.setRightTitle("设置");
        titleBar.setRightIcon(getResources().getDrawable(R.mipmap.ico_set_white));

        //设置底部lineView颜色
//        titleBar.getLineView().setBackgroundResource(R.color.title_bar_red_color);
        //设置底部lineView显示隐藏(默认显示)
//        titleBar.getLineView().setVisibility(View.GONE);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View view) {
                Toast.makeText(TitleViewAarActivity.this, "onLeftClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTitleClick(View view) {
                Toast.makeText(TitleViewAarActivity.this, "onTitleClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick(View view) {
                Toast.makeText(TitleViewAarActivity.this, "onRightClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        //根据titleBar颜色设置状态栏沉浸
        mImmersionBar.titleBar(R.id.title_view).init();
    }
}