package com.example.haochuanxin.selectdorm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class fourActivity extends AppCompatActivity {
    private String st1;
    private information inform;
    private static final int INFORMATION=1;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;

    private Handler mHandler=new Handler(){
        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case INFORMATION:
                    updateTextview();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        st1=getIntent().getStringExtra("number");
        querySelectCode();


    }
    private void querySelectCode() {
        final String address = "https://api.mysspku.com/index.php/V1/MobileCourse/getDetail?stuid=" + st1;
        Log.d("Login", address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection con = null;
                inform = new information();
                try {

                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                    HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());

                    URL url = new URL(address);
                    con = (HttpsURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    InputStream in = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String str;
                    while ((str = reader.readLine()) != null) {
                        response.append(str);
                        Log.d("INFORMATION", str);
                    }
                    String responseStr = response.toString();
                    Log.d("INFORMATION", responseStr);

                    inform = parseJSON(responseStr);
                    if (inform != null) {
                        Log.d("inform", inform.toString());
                        Message msg = new Message();
                        msg.what = INFORMATION;
                        msg.obj = inform;
                        mHandler.sendMessage(msg);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }
            }
        }).start();
    }

    private information parseJSON(String string1) throws JSONException {
        information inform1=new information();
        JSONObject json1=new JSONObject(string1);
        string1=json1.getString("data");
        JSONObject json2=new JSONObject(string1);
        inform1.setId(json2.getString("studentid"));
        inform1.setName(json2.getString("name"));
        inform1.setGender(json2.getString("gender"));
        inform1.setVcode(json2.getString("vcode"));
        inform1.setRoom(json2.getString("room"));
        inform1.setBuilding(json2.getString("building"));
        inform1.setLocation(json2.getString("location"));
        inform1.setGrade(json2.getString("grade"));
        return inform1;

    }

    private void updateTextview(){
        tv1=(TextView)findViewById(R.id.text2);
        tv2=(TextView)findViewById(R.id.text4);
        tv3=(TextView)findViewById(R.id.text6);
        tv4=(TextView)findViewById(R.id.text8);
        tv5=(TextView)findViewById(R.id.text10);
        tv6=(TextView)findViewById(R.id.text12);
        tv7=(TextView)findViewById(R.id.text14);
        tv8=(TextView)findViewById(R.id.text16);
        tv1.setText(inform.getId());
        tv2.setText(inform.getName());
        tv3.setText(inform.getGender());
        tv4.setText(inform.getVcode());
        tv5.setText(inform.getRoom());
        tv6.setText(inform.getBuilding());
        tv7.setText(inform.getLocation());
        tv8.setText(inform.getGrade());


    }




}
