package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yunxiao.base.YxBaseActivity;
import com.yunxiao.scan.CaptureActivity;

public class MainActivity extends YxBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mText).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
            startActivity(intent);
        });

    }
}