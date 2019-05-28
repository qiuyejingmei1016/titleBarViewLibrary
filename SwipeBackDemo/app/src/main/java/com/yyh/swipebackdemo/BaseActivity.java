package com.yyh.swipebackdemo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;

/**
 * 侧滑的父Activity
 *
 * @author xiechengfa2000@163.com
 * @ClassName: BaseActivity
 * @Description:
 * @date 2015-4-25 下午11:04:43
 */
public class BaseActivity extends AppCompatActivity implements SwipeBackLayout.SwipeBackCallBack {
    public SwipeBackLayout swipeBackLayout;


    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		overridePendingTransition(R.anim.activity_ani_enter, 0);

        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().getDecorView().setBackgroundDrawable(null);
        swipeBackLayout = (SwipeBackLayout) LayoutInflater.from(this).inflate(R.layout.swipeback_base_view, null);
        swipeBackLayout.attachToActivity(this);
        //设置侧滑返回finish监听事件
        swipeBackLayout.setCallBack(this);

        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();

        //解决白色状态栏问题
//        mImmersionBar.statusBarDarkFont(true, 0.5f);
//        mImmersionBar.with(this)
//                .statusBarDarkFont(true, 0.2f)
//                .init();
    }

    /**
     * 是否可以使用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    //    @Override
//    public void finish() {
//        super.finish();
////		overridePendingTransition(0, R.anim.activity_ani_exist);
//    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }


    @Override
    public void onSwipeBackFinishListener() {
        Log.e("===========finish", " onSwipeBackFinishListener");
        Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show();
    }

    public void setSwipeBackEnable(boolean enable) {
        swipeBackLayout.setEnableGesture(enable);
    }

    /**
     * 返回键调成此方法
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//		overridePendingTransition(0, R.anim.activity_ani_exist);
    }
}
