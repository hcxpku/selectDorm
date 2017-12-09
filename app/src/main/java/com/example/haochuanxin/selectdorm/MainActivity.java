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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;






public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt1;
    public EditText ev1;
    public EditText ev2;
    private static final int LOGIN=1;
    private static final int WRONG=2;
    private Login login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=(Button)findViewById(R.id.button1);
        ev1=(EditText)findViewById(R.id.edit1);
        ev2=(EditText)findViewById(R.id.edit2);
        if(NetUtil.getNetworkState(this)!=NetUtil.NETWORN_NONE){
            Log.d("login","网络OK");
            Toast.makeText(MainActivity.this, "网络OK", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("login","网络挂了");
            Toast.makeText(MainActivity.this, "网络挂了", Toast.LENGTH_SHORT).show();
        }

        bt1.setOnClickListener(this);
    }
    private Handler mHandler=new Handler(){
        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case LOGIN: {
                    open();
                    break;
                }
                case WRONG:{
                    Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button1) {
            String str1 =ev1.getText().toString();
            String str2 = ev2.getText().toString();
            queryLoginCode(str1, str2);
        }


    }
    private void queryLoginCode(String username,String password)  {
        final String address = "https://api.mysspku.com/index.php/V1/MobileCourse/Login?username="+username+"&password="+password;
        Log.d("Login", address);
        Log.d("Login", username);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection con=null;
                login=new Login();
                try{

                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                    HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());



                    URL url=new URL(address);
                    con=(HttpsURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    InputStream in=con.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String str;
                    while((str=reader.readLine())!=null){
                        response.append(str);
                        Log.d("login", str);
                    }
                    String responseStr=response.toString();
                    Log.d("login", responseStr);
                    login=parseJSON(responseStr);
                    if(login.getErrcode()==0){
                        Log.d("login",login.toString());
                        Message msg=new Message();
                        msg.what=LOGIN;
                        msg.obj=login;
                        mHandler.sendMessage(msg);

                    }
                    else{
                        Log.d("login",login.toString());
                        Message msg=new Message();
                        msg.what=WRONG;
                        msg.obj=login;
                        mHandler.sendMessage(msg);

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
            }
        }).start();
    }
    private Login parseJSON(String jsonData) throws JSONException {
        JSONObject jObj=new JSONObject(jsonData);
        Login login=new Login();
        int errCode=jObj.getInt("errcode");
        login.setErrcode(errCode);
        return login;

    }
    private void open(){
        Intent i=new Intent(MainActivity.this,thirdActivity.class);
        i.putExtra("number",ev1.getText().toString());
        startActivity(i);
    }

}
class MyTrustManager implements X509TrustManager
{
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // TODO Auto-generated method stub
    }
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // TODO Auto-generated method stub
    }
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub
        return null;
    }

}
class MyHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        // TODO Auto-generated method stub
        return true;
    }

}




