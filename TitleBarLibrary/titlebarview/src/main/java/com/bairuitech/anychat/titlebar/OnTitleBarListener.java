package com.bairuitech.anychat.titlebar;

import android.view.View;

/**
 * @describe: TitleBar相关点击回调接口
 * @author: yyh
 * @createTime: 2019/5/27 17:50
 * @className: OnTitleBarListener
 */
public interface OnTitleBarListener {

    /**
     * 左项被点击
     */
    void onLeftClick(View v);

    /**
     * 标题被点击
     */
    void onTitleClick(View v);

    /**
     * 右项被点击
     */
    void onRightClick(View v);
}