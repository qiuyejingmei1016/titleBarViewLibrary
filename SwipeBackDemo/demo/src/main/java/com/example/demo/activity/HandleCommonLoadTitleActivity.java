package com.example.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.UIAction;

/**
 * @describe: 普通titleView 用改方式ImmersionBar.statusBarColor("#ec1d06").init()  可以解决显示问题
 * @author: ASUS
 * @createTime: 2019/4/16 17:09
 * @className: HandleCommonLoadTitleActivity
 */
public class HandleCommonLoadTitleActivity extends BaseActivity implements View.OnClickListener {

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
        textView.setText("普通title_view 显示问题解决");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        //自定义的titleBar是父布局是LinearLayout
//        mImmersionBar.titleBar(R.id.title_bar_view).init();

        //自定义的titleBar是父布局是RelativeLayout适配沉浸方式
        mImmersionBar.fitsSystemWindows(true) //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .statusBarColor(R.color.title_bar_red_color)
                .init();
    }

    @Override
    public void onClick(View v) {

    }
}
