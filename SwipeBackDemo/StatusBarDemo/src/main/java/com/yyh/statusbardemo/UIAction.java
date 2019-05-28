package com.yyh.statusbardemo;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @describe: UI常用方法封装
 * @author: yyh
 * @createTime: 2018/8/3 16:47
 * @className: UIAction
 */
public class UIAction {

    public static void setTitle(Activity activity, String title) {
        if (activity == null) {
            return;
        }
        TextView textView = (TextView) activity.findViewById(R.id.title_text);
        if (textView == null) {
            return;
        }
        textView.setText(title);
    }

    public static void setTitle(Activity activity, int titleTextResId) {
        if (activity == null) {
            return;
        }
        TextView textView = (TextView) activity.findViewById(R.id.title_text);
        if (textView == null) {
            return;
        }
        textView.setText(titleTextResId);
    }

    public static void setTitle(Activity activity, int titleTextResId, View.OnClickListener clickListener) {
        if (activity == null) {
            return;
        }
        TextView textView = (TextView) activity.findViewById(R.id.title_text);
        if (textView == null) {
            return;
        }
        textView.setOnClickListener(clickListener);
        textView.setText(titleTextResId);
    }

    public static ImageButton setTitleBarLeftImgBtn(View view, int iconResId, View.OnClickListener clickListener) {
        ImageButton btn = (ImageButton) view.findViewById(R.id.title_left_img_btn);
        if (btn != null) {
            btn.setImageResource(iconResId);
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(clickListener);
        }
        return btn;
    }

    public static ImageButton setTitleBarRightImgBtn(View view, int iconResId, View.OnClickListener clickListener) {
        ImageButton btn = (ImageButton) view.findViewById(R.id.title_right_img_btn);
        if (btn != null) {
            btn.setImageResource(iconResId);
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(clickListener);
        }
        return btn;
    }

    public static void setBackgroundResourceSafety(View view, int resId) {
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        view.setBackgroundResource(resId);
        if (paddingLeft != 0 || paddingTop != 0 || paddingRight != 0 || paddingBottom != 0) {
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }
}