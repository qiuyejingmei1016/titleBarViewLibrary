package com.yyh.statusbardemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yyh.statusbardemo.BaseActivity;
import com.yyh.statusbardemo.R;
import com.yyh.statusbardemo.UIAction;

/**
 * @describe: 解决白色状态栏字体
 * @author: yyh
 * @createTime: 2018/9/7 15:19
 * @className: WhiteStatusActivity
 */
public class WhiteStatusActivity extends BaseActivity implements View.OnClickListener {

    private boolean mHasSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIAction.setTitle(this, "解决白色状态栏字体");
        ((TextView) findViewById(R.id.title_text)).setTextColor(getResources().getColor(R.color.black));
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.back_black, this);
        findViewById(R.id.btn_white_status_bar).setOnClickListener(this);
        UIAction.setBackgroundResourceSafety(findViewById(R.id.title_bar_view), R.color.white);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.title_bar_view).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_white_status;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_white_status_bar) {
            if (!mHasSet) {
                findViewById(R.id.btn_white_status_bar).setOnClickListener(this);
                UIAction.setBackgroundResourceSafety(findViewById(R.id.title_bar_view), R.color.white);
                ((TextView) findViewById(R.id.title_text)).setTextColor(getResources().getColor(R.color.black));
                UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.back_black, this);
                mImmersionBar.statusBarDarkFont(true, 0.5f).init();
            } else {
                findViewById(R.id.btn_white_status_bar).setOnClickListener(this);
                UIAction.setBackgroundResourceSafety(findViewById(R.id.title_bar_view), R.color.red);
                ((TextView) findViewById(R.id.title_text)).setTextColor(getResources().getColor(R.color.white));
                UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.back_white, this);
                mImmersionBar.statusBarDarkFont(false).init();
            }
            mHasSet = !mHasSet;
        } else if (viewId == R.id.title_left_img_btn) {
            finish();
        }
    }
}
