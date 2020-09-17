package com.yyh.swipebackdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yyh.swipebackdemo.BaseActivity;
import com.yyh.swipebackdemo.R;

public class Main3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button button = (Button) findViewById(R.id.bt);
        button.setText("Main3Activity");
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                startActivity(intent);
            }
        });
    }
}
