package com.yyh.swipebackdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yyh.swipebackdemo.BaseActivity;
import com.yyh.swipebackdemo.R;

public class Main2Activity extends BaseActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }
        });
        mTextView = (TextView) findViewById(R.id.textview);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String notification = intent.getStringExtra("notification");
        String where = intent.getStringExtra("where");
        mTextView.setText(notification + " \n" + where);
    }
}
