package com.yyh.statusbardemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.yyh.statusbardemo.BaseActivity;
import com.yyh.statusbardemo.R;
import com.yyh.statusbardemo.UIAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @describe: 解决EditText和软键盘的问题
 *  @author: yyh
 *  @createTime: 2018/9/7 14:20
 *  @className:  KeyBoardActivity
 */
public class KeyBoardActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout layout;
    ListView listView;
    private List<Map<String, Object>> mapList;
    private View mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIAction.setTitle(this, "解决EditText和软键盘的问题");
        UIAction.setTitleBarLeftImgBtn(getWindow().getDecorView(), R.mipmap.back_white, this);
        listView = (ListView) findViewById(R.id.list_view);
        initData();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_key_board;
    }

//    @Override
//    protected boolean isImmersionBarEnabled() {
//        return false;
//    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mTitleView = findViewById(R.id.title_bar_view);
        mImmersionBar.titleBar(mTitleView)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .init();
//        解决软键盘与底部输入框冲突问题，或者使用以下方法，任选其一
//        KeyboardPatch.patch(this).enable();
//        KeyboardPatch.patch(this,layout).enable();  //layout指的是当前布局的根节点
    }

    protected void initData() {
        mapList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("desc", "我是假数据" + i);
            mapList.add(map);
        }
        listView.setAdapter(new SimpleAdapter(this, mapList,
                R.layout.item_simple_view, new String[]{"desc"}, new int[]{R.id.text}));
    }

    @Override
    public void onClick(View v) {

    }
}
