package com.bairuitech.anychat.titlebar;

/**
 * @describe: TitleBar默认样式设置相关(字体大小颜色等)
 * @author: yyh
 * @createTime: 2019/5/27 17:49
 * @className: DefaultStyle
 */
public class DefaultStyle implements ITitleBarStyle {

    @Override
    public int getTitleBarHeight() {
        return 0;
    }

    @Override
    public int getBackgroundColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getBackIconResource() {
        return R.mipmap.bar_icon_back_black;
    }

    @Override
    public int getLeftViewColor() {
        return 0xFF666666;
    }

    @Override
    public int getTitleViewColor() {
        return 0xFF222222;
    }

    @Override
    public int getRightViewColor() {
        return 0xFFA4A4A4;
    }

    @Override
    public float getLeftViewSize() {
        return 14;
    }

    @Override
    public float getTitleViewSize() {
        return 17;
    }

    @Override
    public float getRightViewSize() {
        return 14;
    }

    @Override
    public int getLeftViewBackground() {
        return R.drawable.bar_selector_selectable_white;
    }

    @Override
    public int getRightViewBackground() {
        return R.drawable.bar_selector_selectable_white;
    }

    @Override
    public boolean getLineVisibility() {
        return true;
    }

    @Override
    public int getLineBackgroundColor() {
        return 0xFFECECEC;
    }
}
