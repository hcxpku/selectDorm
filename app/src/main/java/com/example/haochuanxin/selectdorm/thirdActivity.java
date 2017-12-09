package com.example.haochuanxin.selectdorm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class thirdActivity extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private TextView tv1;
    private String st1;
    private static String ST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        st1=getIntent().getStringExtra("number");
        setContentView(R.layout.activity_third);
        bt1=(Button)findViewById(R.id.dorm);
        bt2=(Button)findViewById(R.id.infro);
        bt3=(Button)findViewById(R.id.bed);
        tv1=(TextView)findViewById(R.id.text);
        if (st1!=null) {
            tv1.setText("欢迎 " + st1 + "！");
            ST=st1;
        }
        else {
            tv1.setText("欢迎"+ST+"！");
        }
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(thirdActivity.this,secondActivity.class);
                startActivity(i);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(thirdActivity.this,fourActivity.class);
                i.putExtra("number", ST);
                startActivity(i);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(thirdActivity.this,fiveActivity.class);
                startActivity(i);
            }
        });
    }



}
