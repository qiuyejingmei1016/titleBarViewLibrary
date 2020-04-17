package com.yyh.statusbardemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yyh.statusbardemo.BaseActivity;
import com.yyh.statusbardemo.R;
import com.yyh.statusbardemo.UIAction;

import static com.gyf.barlibrary.ImmersionBar.with;

/**
 *  @describe: 全屏图片
 *  @author: yyh
 *  @createTime: 2018/9/6 10:15
 *  @className:  FullPicActivity
 */
public class FullPicActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private View mTitleBarView;
    private SeekBar mSeekBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UIAction.setTitle(this, "全屏图片");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.back_white, this);

        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        textView = (TextView) findViewById(R.id.text_view);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_full_pic;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mTitleBarView = findViewById(R.id.title_bar_view);
        UIAction.setBackgroundResourceSafety(mTitleBarView, R.color.tans);
        mImmersionBar.titleBar(mTitleBarView, false)
                .transparentBar().keyboardEnable(true)
                .init();
//        mImmersionBar.statusBarView(mTitleBarView)
//                .navigationBarColor(R.color.tab_bottom_normal)
//                .addTag("PicAndColor")  //给上面参数打标记，以后可以通过标记恢复
//                .init();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float alpha = (float) progress / 100;
        textView.setText("透明度:" + alpha + "f");
        with(FullPicActivity.this)
                .addViewSupportTransformColor(mTitleBarView, R.color.colorPrimary)
                .navigationBarColorTransform(R.color.orange)
                .barAlpha(alpha)
                .init();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
