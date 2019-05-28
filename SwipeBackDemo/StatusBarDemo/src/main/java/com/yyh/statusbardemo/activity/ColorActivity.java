package com.yyh.statusbardemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yyh.statusbardemo.BaseActivity;
import com.yyh.statusbardemo.R;
import com.yyh.statusbardemo.UIAction;

/**
 *  @describe: 彩色状态栏导航栏
 *  @author: yyh
 *  @createTime: 2018/9/5 15:52
 *  @className:  ColorActivity
 */
public class ColorActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar mSeekBar;
    private TextView mTextView;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private View mTitleBarView;
    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIAction.setTitle(this, "彩色状态栏导航栏");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.back_white, this);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mTextView = (TextView) findViewById(R.id.text_view);
        mRootView = findViewById(R.id.root_view);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_color;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mTitleBarView = findViewById(R.id.title_bar_view);
        mImmersionBar.titleBar(mTitleBarView)
//                .statusBarColor(R.color.colorPrimary)
                .navigationBarColor(R.color.btn8)
                .init();
//        mImmersionBar.statusBarView(mTitleBarView)
//                .statusBarColor(R.color.colorPrimary)
//                .navigationBarColor(R.color.btn8)
//                .init();
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn1) {

        } else if (viewId == R.id.btn2) {

        } else if (viewId == R.id.btn3) {

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float alpha = (float) progress / 100;
        mTextView.setText("透明度:" + alpha + "f");
        mImmersionBar.barAlpha(alpha)
                .statusBarColorTransform(R.color.btn14)
                .navigationBarColorTransform(R.color.btn3)
                .addViewSupportTransformColor(mTitleBarView)
                .addViewSupportTransformColor(btn1, R.color.btn1, R.color.btn4)
                .addViewSupportTransformColor(btn2, R.color.btn3, R.color.btn12)
                .addViewSupportTransformColor(btn3, R.color.btn5, R.color.btn10)
                .addViewSupportTransformColor(mRootView, R.color.darker_gray, R.color.btn5)
                .init();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
