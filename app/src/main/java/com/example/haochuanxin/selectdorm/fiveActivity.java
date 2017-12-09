package com.example.haochuanxin.selectdorm;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class fiveActivity extends AppCompatActivity {
   private Button bt1;
   private EditText ed1;
   private String st1;
   private int code;
   private bed bed1;
   private static final int BED=1;
   private TextView tv1,tv2,tv3,tv4,tv5;

    private Handler mHandler=new Handler(){
        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case BED:
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
        setContentView(R.layout.activity_five);
        ed1=(EditText)findViewById(R.id.text);
        bt1=(Button)findViewById(R.id.button);
        tv1=(TextView)findViewById(R.id.text2);
        tv2=(TextView)findViewById(R.id.text4);
        tv3=(TextView)findViewById(R.id.text6);
        tv4=(TextView)findViewById(R.id.text8);
        tv5=(TextView)findViewById(R.id.text10);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               code=Integer.parseInt(ed1.getText().toString());
               System.out.print(code);
                querySelectBed();

            }
        });
    }

    private void querySelectBed() {
        final String address = "https://api.mysspku.com/index.php/V1/MobileCourse/getRoom?gender=" +code;
        Log.d("bed", address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection con = null;
                bed1 = new bed();
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
                        Log.d("bed", str);
                    }
                    String responseStr = response.toString();
                    Log.d("bed", responseStr);

                    bed1= parseJSON(responseStr);
                    if (bed1!= null) {
                        Log.d("bed", bed1.toString());
                        Message msg = new Message();
                        msg.what = BED;
                        msg.obj = bed1;
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

    private bed parseJSON(String string1) throws JSONException {
        bed bed1=new bed();
        JSONObject json1=new JSONObject(string1);
        string1=json1.getString("data");
        JSONObject json2=new JSONObject(string1);
        bed1.setFive(json2.getInt("5"));
        bed1.setEight(json2.getInt("8"));
        bed1.setNine(json2.getInt("9"));
        bed1.setThirteen(json2.getInt("13"));
        bed1.setForteen(json2.getInt("14"));
        return bed1;

    }

    private void updateTextview(){
        tv1.setText(String.valueOf(bed1.getFive()));
        tv2.setText(String.valueOf(bed1.getEight()));
        tv3.setText(String.valueOf(bed1.getNine()));
        tv4.setText(String.valueOf(bed1.getThirteen()));
        tv5.setText(String.valueOf(bed1.getForteen()));


    }

}
