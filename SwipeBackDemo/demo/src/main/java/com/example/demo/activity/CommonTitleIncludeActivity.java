package com.example.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.UIAction;

/**
 * @describe: CommonTitle布局include测试
 * @author: yyh
 * @createTime: 2019/5/30 10:00
 * @className: CommonTitleIncludeActivity
 */
public class CommonTitleIncludeActivity extends BaseActivity implements View.OnClickListener {

    private Button mHandleView;

    private boolean mIsWhiteStatusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_common_title;
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

    private void initView() {
        UIAction.setTitle(this, "CommonTitle");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.ico_back_white, this);
        UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.ico_set_white, this);

        this.mHandleView = (Button) findViewById(R.id.bt_handle);
        this.mHandleView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.bt_handle) {
            if (!mIsWhiteStatusBar) {
                UIAction.setBackgroundResourceSafety(findViewById(R.id.title_bar_view), R.color.white_color);
                ((TextView) findViewById(R.id.title_text)).setTextColor(getResources().getColor(R.color.black_color));
                UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.ico_back_black, this);
                UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.ico_set_black, this);
                mHandleView.setText("白色状态栏");

                //自定义的titleBar是父布局是RelativeLayout时需要定义statusBarColor();
                mImmersionBar.statusBarDarkFont(true, 0.5f).statusBarColor(R.color.white_color).init();
            } else {
                UIAction.setBackgroundResourceSafety(findViewById(R.id.title_bar_view), R.color.title_bar_red_color);
                ((TextView) findViewById(R.id.title_text)).setTextColor(getResources().getColor(R.color.white_color));
                UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.ico_back_white, this);
                UIAction.setTitleBarRightImgBtn(getWindow().getDecorView(), R.mipmap.ico_set_white, this);
                mHandleView.setText("默认");

                //自定义的titleBar是父布局是RelativeLayout时需要定义statusBarColor();
                mImmersionBar.statusBarDarkFont(false).statusBarColor(R.color.title_bar_red_color).init();
            }
            mIsWhiteStatusBar = !mIsWhiteStatusBar;
        }
    }
}
