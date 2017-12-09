package com.example.haochuanxin.selectdorm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class secondActivity extends AppCompatActivity {
    private EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
    private totalMan total;
    private Button bt1;
    private static final int TOTAL=1;

    private Handler mHandler=new Handler(){
        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case TOTAL:
                    Toast.makeText(secondActivity.this,"选择成功！",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(secondActivity.this,thirdActivity.class);
                    startActivity(i);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ed1=(EditText)findViewById(R.id.edit1);
        ed2=(EditText)findViewById(R.id.edit2);
        ed3=(EditText)findViewById(R.id.edit3);
        ed4=(EditText)findViewById(R.id.edit4);
        ed5=(EditText)findViewById(R.id.edit5);
        ed6=(EditText)findViewById(R.id.edit6);
        ed7=(EditText)findViewById(R.id.edit7);
        ed8=(EditText)findViewById(R.id.edit8);
        bt1=(Button)findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
                querySelectCode();

            }
        });
    }

    private void querySelectCode() {
        final String address = "https://api.mysspku.com/index.php/V1/MobileCourse/SelectRoom";
        Log.d("Login", address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try{
                   URL url=new URL(address);
                   connection=(HttpURLConnection)url.openConnection();
                   connection.setConnectTimeout(8000);
                   connection.setRequestMethod("POST");
                   String data=total.toString();
                   connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                   connection.setRequestProperty("Content-Length",data.length()+"");
                   connection.setDoOutput(true);
                    OutputStream outputStream=connection.getOutputStream();
                    outputStream.write(data.getBytes());
                    int responseCode=connection.getResponseCode();
                    Log.d("Dorm",String.valueOf(responseCode));
                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String str;
                    while ((str=reader.readLine())!=null){
                        response.append(str);
                    }
                    String jData=response.toString();
                    Log.d("Dorm2",jData);
                    int errcode=parseJSON(jData);
                    if (errcode==0){
                        Message msg = new Message();
                        msg.what =TOTAL;
                        msg.obj =errcode;
                        mHandler.sendMessage(msg);
                    }






                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();




    }
    private int parseJSON(String str) throws JSONException {
        JSONObject json1=new JSONObject(str);
        int errCode=json1.getInt("errcode");
        return errCode;
    }
    public void init(){
        total=new totalMan();
        if(ed1.getText().toString()!=null) {
            total.setName(ed1.getText().toString());
        }
        if(ed2.getText().toString()!=null) {
            total.setNum1(ed2.getText().toString());
        }
        if(ed3.getText().toString()!=null) {
            total.setVcode1(ed3.getText().toString());
        }
        if(ed4.getText().toString()!=null){
        total.setNum2(ed4.getText().toString());
        }
        if (ed5.getText().toString()!=null) {
            total.setVcode2(ed5.getText().toString());
        }
        if (ed6.getText().toString()!=null) {
            total.setNum3(ed6.getText().toString());
        }
        if (ed7.getText().toString()!=null) {
            total.setVcode3(ed7.getText().toString());
        }
        if (ed8.getText().toString()!=null) {
            total.setBuildingNo(Integer.parseInt(ed8.getText().toString()));
        }



    }
}
