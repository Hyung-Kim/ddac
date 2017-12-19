package com.example.taehyung.ddac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import android.os.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnFindIdAndPwd, btnRegister;
    private String TAG ="KTH";
    EditText etId;
    public static Handler mHandler = new Handler();
    EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        resourceInit();
        registerListener();
        // Log.d(TAG, Locale.getDefault().getLanguage());
//        this.finish();
//        Intent intent = new Intent(this, DDACMainActivity.class);
//        startActivity(intent);
    }

    View.OnClickListener loginClickListener = (v) -> {
        String id = etId.getText().toString();
        String pw = etPassword.getText().toString();
        if(id.equals("guest") && pw.equals("guest")){
            this.finish();
            Intent intent = new Intent(this, DDACMainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
        }
    };
    View.OnClickListener registerClickListener = (v) -> {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    };
    View.OnClickListener findClickListener = (v) -> {
        Intent intent = new Intent(this, FindUserInfoActivity.class);
        startActivity(intent);
    };
    void registerListener(){
        btnLogin.setOnClickListener(loginClickListener);
        btnRegister.setOnClickListener(registerClickListener);
        btnFindIdAndPwd.setOnClickListener(findClickListener);
    }
    void resourceInit(){
        etId = (EditText)findViewById(R.id.login_id);
        etPassword = (EditText)findViewById(R.id.login_passwd);
        btnLogin = (Button)findViewById(R.id.login_button);
        btnFindIdAndPwd = (Button)findViewById(R.id.findIdAndPassword);
        btnRegister = (Button)findViewById(R.id.register);
    }
}
