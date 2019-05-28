package com.yyh.statusbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.BarParams;
import com.yyh.statusbardemo.activity.ColorActivity;
import com.yyh.statusbardemo.activity.FullPicActivity;
import com.yyh.statusbardemo.activity.KeyBoardActivity;
import com.yyh.statusbardemo.activity.PicAndColorActivity;
import com.yyh.statusbardemo.activity.ShapeQQActivity;
import com.yyh.statusbardemo.activity.WebActivity;
import com.yyh.statusbardemo.activity.WhiteStatusActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout drawer;
    private int windowsWight;
    private int windowsHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIAction.setTitle(this, "主页");
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
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
        if (viewId == R.id.btn_white_status_bar) {//解决白色状态栏字体
            startActivity(new Intent(this, WhiteStatusActivity.class));
        } else if (viewId == R.id.btn_set_status_bar) {//导航栏设置
            startActivity(new Intent(this, PicAndColorActivity.class));
        } else if (viewId == R.id.btn_color) {//彩色状态栏导航栏
            startActivity(new Intent(this, ColorActivity.class));
        } else if (viewId == R.id.btn_full_pic) {//全屏图片
            startActivity(new Intent(this, FullPicActivity.class));
        } else if (viewId == R.id.btn_shape_qq) {//仿QQ状态栏和标题栏渐变色
            startActivity(new Intent(this, ShapeQQActivity.class));
        } else if (viewId == R.id.btn_drawer) {//侧滑菜单
            drawer.openDrawer(Gravity.START);
        } else if (viewId == R.id.ll_github) {//侧滑菜单选项
            Toast.makeText(this, "ll_github", Toast.LENGTH_SHORT).show();
        } else if (viewId == R.id.profile_image) {//侧滑菜单选项
            Toast.makeText(this, "profile_image", Toast.LENGTH_SHORT).show();
        } else if (viewId == R.id.btn_web) {//侧滑菜单选项
            startActivity(new Intent(this, WebActivity.class));
        } else if (viewId == R.id.btn_key_board) {//解决EditText和软键盘的问题
            startActivity(new Intent(this, KeyBoardActivity.class));
        } else if (viewId == R.id.btn_status_hide) {//隐藏状态栏
            mImmersionBar.hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();
        } else if (viewId == R.id.btn_navigation_hide) {//隐藏导航栏
            if (mImmersionBar.hasNavigationBar(this)) {
                mImmersionBar.hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init();
            } else {
                Toast.makeText(this, "当前设备没有导航栏或者低于4.4系统", Toast.LENGTH_SHORT).show();
            }
        } else if (viewId == R.id.btn_bar_hide) {//隐藏全部
            mImmersionBar.hideBar(BarHide.FLAG_HIDE_BAR).init();
        } else if (viewId == R.id.btn_bar_show) {//恢复显示
            mImmersionBar.hideBar(BarHide.FLAG_SHOW_BAR).init();
        } else if (viewId == R.id.btn_full) {//Activity全屏显示
            if (mImmersionBar.hasNavigationBar(this)) {
                BarParams barParams = mImmersionBar.getBarParams();
                if (barParams.fullScreen) {
                    mImmersionBar.fullScreen(false).navigationBarColor(R.color.black).init();
                } else {
                    mImmersionBar.fullScreen(true).transparentNavigationBar().init();
                }
            } else {
                Toast.makeText(this, "当前设备没有导航栏或者低于4.4系统", Toast.LENGTH_SHORT).show();
            }
        }
    }
}