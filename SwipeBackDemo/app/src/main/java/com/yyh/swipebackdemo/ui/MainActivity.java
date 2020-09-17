package com.yyh.swipebackdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yyh.swipebackdemo.BaseActivity;
import com.yyh.swipebackdemo.PushNotificationHelper;
import com.yyh.swipebackdemo.R;

public class MainActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //状态栏
        mImmersionBar.titleBar(R.id.title_bar).init();

        Button button = (Button) findViewById(R.id.bt);
        button.setText("MainActivity");
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        mTextView = (TextView) findViewById(R.id.textview);

        initData();

        initNotification();
    }

    private void initData() {
        Intent intent = getIntent();
        String notification = intent.getStringExtra("notification");
        String where = intent.getStringExtra("where");
        mTextView.setText(notification + " \n" + where);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PushNotificationHelper helper =
                    PushNotificationHelper.getInstance((int) System.currentTimeMillis(), MainActivity.this);
            helper.showNotify();
        }
    };
    private void initNotification() {
        handler.sendEmptyMessageDelayed(1,5000);
    }
}
