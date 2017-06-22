package com.example.android.multi_language.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.multi_language.R;

import static com.example.android.multi_language.R.string.email;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailEdit;
    private EditText mPwdEdit;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getActionBar().hide();


        mEmailEdit = (EditText) findViewById(R.id.input_email);
        mPwdEdit = (EditText) findViewById(R.id.input_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);




        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, MainActivity.class));

//                String email = mEmailEdit.getText().toString().trim();
//                String pwd = mPwdEdit.getText().toString().trim();
//                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)) {
//
//                    Toast.makeText(getApplicationContext(), "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    if (email.equals("liubin@qq.com") && pwd.equals("123")) {
//
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    } else {
//
//                        Toast.makeText(getApplicationContext(), "账号或密码错误", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });



    }
}
