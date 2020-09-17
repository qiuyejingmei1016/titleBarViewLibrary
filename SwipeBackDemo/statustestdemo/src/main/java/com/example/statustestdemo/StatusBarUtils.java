package com.example.statustestdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;


/**
 * 状态栏工具类
 */
public class StatusBarUtils {
    private Activity mActivity;
    //状态栏颜色
    private int mColor = -1;
    //状态栏drawble
    private Drawable mDrawable;
    //是否是最外层布局是 DrawerLayout 的侧滑菜单
    private boolean mIsDrawerLayout;
    //是否包含 ActionBar
    private boolean mIsActionBar;
    //侧滑菜单页面的内容视图
    private int mContentResourseIdInDrawer;

    public StatusBarUtils(Activity activity) {
        mActivity = activity;
    }

    public static StatusBarUtils with(Activity activity) {
        return new StatusBarUtils(activity);
    }

    public int getColor() {
        return mColor;
    }

    public StatusBarUtils setColor(int color) {
        mColor = color;
        return this;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public StatusBarUtils setDrawable(Drawable drawable) {
        mDrawable = drawable;
        return this;
    }

    public boolean isDrawerLayout() {
        return mIsDrawerLayout;
    }

    public boolean isActionBar() {
        return mIsActionBar;
    }

    public StatusBarUtils setIsActionBar(boolean actionBar) {
        mIsActionBar = actionBar;
        return this;
    }

    /**
     * 是否是最外层布局为 DrawerLayout 的侧滑菜单
     *
     * @param drawerLayout 是否最外层布局为 DrawerLayout
     * @param contentId    内容视图的 id
     * @return
     */
    public StatusBarUtils setDrawerLayoutContentId(boolean drawerLayout, int contentId) {
        mIsDrawerLayout = drawerLayout;
        mContentResourseIdInDrawer = contentId;
        return this;
    }

    public void init() {
        fullScreen(mActivity);
        if (mColor != -1) {
            //设置了状态栏颜色
            addStatusViewWithColor(mActivity, mColor);
        }
        if (mDrawable != null) {
            //设置了状态栏 drawble，例如渐变色
            addStatusViewWithDrawble(mActivity, mDrawable);
        }
        if (isDrawerLayout()) {
            //未设置 fitsSystemWindows 且是侧滑菜单，需要设置 fitsSystemWindows 以解决 4.4 上侧滑菜单上方白条问题
            fitsSystemWindows(mActivity);
        }
        if (isActionBar()) {
            //要增加内容视图的 paddingTop,否则内容被 ActionBar 遮盖
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
                rootView.setPadding(0, getStatusBarHeight(mActivity) + getActionBarHeight(mActivity), 0, 0);
            }
        }
    }

    /**
     * 去除 ActionBar 阴影
     */
    public StatusBarUtils clearActionBarShadow() {
        if (Build.VERSION.SDK_INT >= 21) {
            ActionBar supportActionBar = ((AppCompatActivity) mActivity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setElevation(0);
            }
        }
        return this;
    }

    /**
     * 设置页面最外层布局 FitsSystemWindows 属性
     *
     * @param activity
     */
    private void fitsSystemWindows(Activity activity) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
            //布局预留状态栏高度的 padding
            if (parentView instanceof DrawerLayout) {
                DrawerLayout drawer = (DrawerLayout) parentView;
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                drawer.setClipToPadding(false);
            }
        }
    }

    /**
     * 利用反射获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("getStatusBarHeight", result + "");
        return result;
    }

    /**
     * 获得 ActionBar 的高度
     *
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            TypedValue tv = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return result;
    }

    /**
     * 添加状态栏占位视图
     *
     * @param activity
     */
    private void addStatusViewWithColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isDrawerLayout()) {
                //要在内容布局增加状态栏，否则会盖在侧滑菜单上
                ViewGroup rootView = (ViewGroup) activity.findViewById(android.R.id.content);
                //DrawerLayout 则需要在第一个子视图即内容试图中添加padding
                View parentView = rootView.getChildAt(0);
                LinearLayout linearLayout = new LinearLayout(activity);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                View statusBarView = new View(activity);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getStatusBarHeight(activity));
                statusBarView.setBackgroundColor(color);
                //添加占位状态栏到线性布局中
                linearLayout.addView(statusBarView, lp);
                //侧滑菜单
                DrawerLayout drawer = (DrawerLayout) parentView;
                //内容视图
                View content = activity.findViewById(mContentResourseIdInDrawer);
                //将内容视图从 DrawerLayout 中移除
                drawer.removeView(content);
                //添加内容视图
                linearLayout.addView(content, content.getLayoutParams());
                //将带有占位状态栏的新的内容视图设置给 DrawerLayout
                drawer.addView(linearLayout, 0);
            } else {
                //设置 paddingTop
                ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
                rootView.setPadding(0, getStatusBarHeight(mActivity), 0, 0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //直接设置状态栏颜色
                    activity.getWindow().setStatusBarColor(color);
                } else {
                    //增加占位状态栏
                    ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
                    View statusBarView = new View(activity);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            getStatusBarHeight(activity));
                    statusBarView.setBackgroundColor(color);
                    decorView.addView(statusBarView, lp);
                }
            }
        }
    }

    /**
     * 添加状态栏占位视图
     *
     * @param activity
     */
    private void addStatusViewWithDrawble(Activity activity, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //占位状态栏
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                statusBarView.setBackground(drawable);
            } else {
                statusBarView.setBackgroundDrawable(drawable);
            }
            if (isDrawerLayout()) {
                //要在内容布局增加状态栏，否则会盖在侧滑菜单上
                ViewGroup rootView = (ViewGroup) activity.findViewById(android.R.id.content);
                //DrawerLayout 则需要在第一个子视图即内容试图中添加padding
                View parentView = rootView.getChildAt(0);
                LinearLayout linearLayout = new LinearLayout(activity);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                //添加占位状态栏到线性布局中
                linearLayout.addView(statusBarView, lp);
                //侧滑菜单
                DrawerLayout drawer = (DrawerLayout) parentView;
                //内容视图
                View content = activity.findViewById(mContentResourseIdInDrawer);
                //将内容视图从 DrawerLayout 中移除
                drawer.removeView(content);
                //添加内容视图
                linearLayout.addView(content, content.getLayoutParams());
                //将带有占位状态栏的新的内容视图设置给 DrawerLayout
                drawer.addView(linearLayout, 0);
            } else {
                //增加占位状态栏，并增加状态栏高度的 paddingTop
                ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
                decorView.addView(statusBarView, lp);
                //设置 paddingTop
                ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
                rootView.setPadding(0, getStatusBarHeight(mActivity), 0, 0);
            }
        }
    }

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 通过设置全屏，设置状态栏透明 导航栏黑色
     *
     * @param activity
     */
    public static void setStatusTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();

                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
//                attributes.flags |= flagTranslucentStatus;
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);

                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }


    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";    //OPPO
    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";     //小米
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";       //华为
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";   //锤子
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";          //vivo

    /**
     * 设置状态栏透明度、背景颜色、文字颜色
     *
     * @param isTranslate 是否透明，若为true，则bgColor设置无效
     * @param isDarkText  字体颜色，只有黑白两色，无论什么色值，都只会转为黑白两色
     * @param bgColor     背景色，即状态栏颜色，isTranslate若为true则此值无效
     */
    public static boolean setStatusColor(Activity activity, boolean isTranslate, boolean isDarkText, @ColorRes int bgColor) {
        //MIUI系统
        if (isMIUI6Later()) {
            setMIUI6Translate(activity, isTranslate);
            setMIUI6StatusBarDarkMode(activity, isDarkText);
        } else {
            //如果系统为6.0及以上，使用Google自带的方式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                decorView.setSystemUiVisibility((isTranslate ? View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN : 0) | (isDarkText ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : 0));
                window.setStatusBarColor(isTranslate ? Color.TRANSPARENT : ContextCompat.getColor(activity, bgColor));

            } else {
                //OPPO Color3.0 & Android 5.1
                if (isColorOS_3()) {
                    //控制字体颜色，只有黑白两色
                    final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = isDarkText ? 0x00000010 : 0x00190000;
                    Window window = activity.getWindow();
                    View decorView = window.getDecorView();
                    decorView.setSystemUiVisibility((isTranslate ? View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN : 0) | SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(isTranslate ? Color.TRANSPARENT : ContextCompat.getColor(activity, bgColor));
                    }

                } else if (isFlyme4Later()) {
                    darModeForFlyme4(activity, isDarkText);
                    if (isTranslate) {
                        setStatusTranslate(activity);
                    }
                } else {
                    //其他机型，如华为、三星、索尼、诺基亚、VIVO、锤子、360等
                    if (isTranslate) {
                        setStatusTranslate(activity);
                    }
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean setStatusColor(Activity activity, boolean isTranslate, boolean isDarkText) {
        try {
            return setStatusColor(activity, isTranslate, isDarkText, R.color.transparent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否是MIUI6及以后版本
     *
     * @return 是否是MIUI6
     */
    private static boolean isMIUI6Later() {
        try {
            Class<?> cla = Class.forName("android.os.SystemProperties");
            Method mtd = cla.getMethod("get", String.class);
            String val = (String) mtd.invoke(null, KEY_VERSION_MIUI);
            val = val.replaceAll("[vV]", "");
            int version = Integer.parseInt(val);
            return version >= 6;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置MIUI6及以上状态栏透明,字体为默认白色，Android6.0以上也可以
     *
     * @param on 是否为透明
     */
    private static void setMIUI6Translate(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置MIUI6及以上，Android6.0以下版本状态栏黑色字符
     */
    private static void setMIUI6StatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                if (darkmode) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否是ColorOS_3.0系统 Android 5.1
     *
     * @return 是否是colorOS_3.0
     */
    private static boolean isColorOS_3() {
        try {
            Class<?> cla = Class.forName("android.os.SystemProperties");
            Method mtd = cla.getMethod("get", String.class);
            String val = (String) mtd.invoke(null, KEY_VERSION_OPPO);
            val = val.replaceAll("[vV]", "");
            val = val.substring(0, 1);
            int version = Integer.parseInt(val);
            return version >= 3;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否是魅族Flyme4
     *
     * @return 是否是魅族Flyme4
     */
    private static boolean isFlyme4Later() {
        if ("MEIZU".equals(Build.BRAND.trim().toUpperCase())) {
            return Build.FINGERPRINT.contains("Flyme_OS_4") || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4") || Pattern.compile("Flyme_OS_[4|5]", Pattern.CASE_INSENSITIVE)
                    .matcher(Build.DISPLAY).find();
        }
        return false;
    }

    /**
     * 设置魅族Flyme4以后 状态栏黑色字体
     *
     * @param dark 是否黑色
     */
    private static void darModeForFlyme4(Activity activity, boolean dark) {
        boolean result = false;
        try {
            WindowManager.LayoutParams e = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(e);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(e, value);
            activity.getWindow().setAttributes(e);
        } catch (Exception var8) {
            Log.e("StatusBar", "darkIcon: failed");
        }
    }

    /**
     * 设置状态栏透明
     */
    public static void setStatusTranslate(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置状态栏背景颜色，不适配5.0以下系统
     *
     * @param color 颜色值
     */
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
        }
    }


}