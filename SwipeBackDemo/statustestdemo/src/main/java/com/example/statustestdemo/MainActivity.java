package com.example.statustestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();

        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.ll_1).setOnClickListener(this);
        findViewById(R.id.ll_2).setOnClickListener(this);
        findViewById(R.id.ll_3).setOnClickListener(this);
        findViewById(R.id.ll_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}