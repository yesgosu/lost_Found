package com.example.calender;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class membership extends AppCompatActivity {
    Button btninsert;
    EditText idet, pwet, pwdetch1,nameet;
    String name,password,pwdcheck,name2; // name = id , name = name
    //ImageView setimage; 이미지스킵

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);
        Button delete = (Button) findViewById(R.id.delete);
        setTitle("ORACLE");


        btninsert = (Button) findViewById(R.id.btninsert);
        idet = (EditText) findViewById(R.id.memberid);
        pwet = (EditText) findViewById(R.id.memberpwd);
        pwdetch1 = (EditText) findViewById(R.id.memberpwdok);
        nameet = (EditText)findViewById(R.id.membername);
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = idet.getText().toString();
                name2 = nameet.getText().toString();
                password = pwet.getText().toString();
                pwdcheck = pwdetch1.getText().toString();

                if (nameet.getText().toString().length() == 0) {
                    Toast.makeText(membership.this,"이름을 입력하세요",Toast.LENGTH_SHORT).show();
                    nameet.requestFocus();
                    return;
                }
                if (idet.getText().toString().length() == 0) {
                    Toast.makeText(membership.this,"아이디를 입력하세요",Toast.LENGTH_SHORT).show();
                    idet.requestFocus();
                    return;
                }
                if (pwet.getText().toString().length() == 0) {
                    Toast.makeText(membership.this,"비밀번호를 입력하세요",Toast.LENGTH_SHORT).show();
                    pwet.requestFocus();
                    return;
                }
                if (pwdetch1.getText().toString().length() == 0) {
                    Toast.makeText(membership.this,"다시 입력하세요",Toast.LENGTH_SHORT).show();
                    pwdetch1.requestFocus();
                    return;
                }

                if (!pwet.getText().toString().equals(pwdetch1.getText().toString())) {
                    Toast.makeText(membership.this,"비밀번호가 일치하지않습니다",Toast.LENGTH_SHORT).show();
                    pwet.setText("");
                    pwdetch1.setText("");
                    pwet.requestFocus();
                    return;
                }


                    dataInsert(name2,name, password, pwdcheck);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(membership.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


        void dataInsert(final String name, final String id, final String password, final String pwdcheck) {
            new Thread() {
                public void run() {
                    try {
                        URL setURL = new URL("Http://192.168.123.2/insert.php/");
                        HttpURLConnection http;
                        http = (HttpURLConnection) setURL.openConnection();
                        http.setDefaultUseCaches(false);
                        http.setDoInput(true);
                        http.setRequestMethod("POST");
                        http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("id").append("=").append(name).append("/").append(id).append("/").append(password).append("/");
                        OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
                        outStream.write(buffer.toString());
                        outStream.flush();
                        InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
                        final BufferedReader reader = new BufferedReader(tmp);
                        Intent intent = new Intent(membership.this,MainActivity.class);
                        startActivity(intent);

                        while (reader.readLine() != null) {
                            System.out.println(reader.readLine());
                        }
                    } catch (Exception e) {
                        Log.e("dataInsert()", "지정 에러 발생", e);
                    }
                }
            }.start();
        }

    }

