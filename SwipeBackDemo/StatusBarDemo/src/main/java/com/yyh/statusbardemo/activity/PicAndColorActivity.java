package com.yyh.statusbardemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.yyh.statusbardemo.BaseActivity;
import com.yyh.statusbardemo.R;
import com.yyh.statusbardemo.UIAction;

/**
 *  @describe: 导航栏设置
 *  @author: yyh
 *  @createTime: 2018/9/5 15:52
 *  @className:  PicAndColorActivity
 */
public class PicAndColorActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar mSeekBar;
    private View mTitleBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UIAction.setTitle(this, "修改颜色");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.back_white, this);

        findViewById(R.id.btn_navigation_color).setOnClickListener(this);
        findViewById(R.id.btn_status_color).setOnClickListener(this);
        findViewById(R.id.btn_recover_color).setOnClickListener(this);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pic_and_color;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mTitleBarView = findViewById(R.id.title_bar_view);
        mImmersionBar.titleBar(mTitleBarView)
                .addTag("PicAndColor")  //给上面参数打标记，以后可以通过标记恢复
                .init();
//        mImmersionBar.statusBarView(mTitleBarView)
//                .navigationBarColor(R.color.tab_bottom_normal)
//                .addTag("PicAndColor")  //给上面参数打标记，以后可以通过标记恢复
//                .init();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_navigation_color) {
            if (ImmersionBar.hasNavigationBar(this)) {
                mImmersionBar.navigationBarColor(R.color.trantant).init();
            } else {
                Toast.makeText(this, "当前设备没有导航栏", Toast.LENGTH_SHORT).show();
            }
        } else if (viewId == R.id.btn_status_color) {
            mImmersionBar.statusBarColor(R.color.orchid).init();
        } else if (viewId == R.id.btn_recover_color) {
            mImmersionBar.getTag("PicAndColor").init(); //根据tag标记来恢复
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float alpha = (float) progress / 100;
        mImmersionBar.statusBarColorTransform(R.color.orange)
                .navigationBarColorTransform(R.color.tans)
                .addViewSupportTransformColor(mTitleBarView)
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
