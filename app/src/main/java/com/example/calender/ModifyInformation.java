package com.example.calender;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.calender.requests.auth.PasswordUpdateRequest;
import com.example.calender.requests.auth.WithdrawalRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class ModifyInformation extends AppCompatActivity {

    private final String TAG = "ModifyInformation";

    private String id = null;
    private String password = null;

    private RequestQueue requestQueue;

    private TextInputLayout passwordTextInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
            password = getIntent().getStringExtra("password");
        }

        if (id == null && savedInstanceState != null) {
            id = savedInstanceState.getString("id");
            password = savedInstanceState.getString("password");
        }

        if (id == null) {
            finish();
            return;
        }

        setContentView(R.layout.activity_modify_information);

        requestQueue = Volley.newRequestQueue(this);

        initUi();
    }

    private void initUi() {
        TextInputLayout idTextInput = findViewById(R.id.idTextInput);
        idTextInput.getEditText().setText(id);

        passwordTextInput = findViewById(R.id.passwordTextInput);

        Button withdrawalButton = findViewById(R.id.btnbye);
        withdrawalButton.setOnClickListener(v -> new AlertDialog.Builder(ModifyInformation.this)
                .setTitle("회원탈퇴").setMessage("정말로하시겠습니까?")
                .setPositiveButton("회원탈퇴", (dialog, which) -> withdrawal())
                .setNegativeButton("취소", null)
                .show());

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(v -> updatePassword());
    }


    private void withdrawal() {
        requestQueue.cancelAll(WithdrawalRequest.TAG);
        requestQueue.add(new WithdrawalRequest(
                id,
                response -> {
                    Log.d(TAG, response);

                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getBoolean("success")) {
                            Toast.makeText(this, "회원 탈퇴되었습니다. 이용해 주셔서 감사합니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ModifyInformation.this,MainActivity.class);
                            startActivity(intent);


                            //Intent i = new Intent(ModifyInformation.this, MainActivity.class);
                            //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            //startActivity(i);


                        } else {
                            Toast.makeText(this, "에러가 발생하였습니다. 잠시 후 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "에러가 발생하였습니다. 잠시 후 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                }
        ));
    }

    private void updatePassword() {
        String newPassword = passwordTextInput.getEditText().getText().toString().trim();
        if (newPassword.isEmpty()) return;

        requestQueue.cancelAll(PasswordUpdateRequest.TAG);
        requestQueue.add(new PasswordUpdateRequest(
                id,
                newPassword,
                response -> {
                    Log.d(TAG, response);

                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getBoolean("success")) {
                            Toast.makeText(this, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ModifyInformation.this, scheduleForm.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(this, "비밀번호 변경에 실패하였습니다. 기존 비밀번호와 같은지 확인해 주세요.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "에러가 발생하였습니다. 잠시 후 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                }
        ));
    }

//    private void dataDelete(String id) {
//        new Thread(() -> {
//            try {
//                URL setURL = new URL("Http://192.168.123.2/delete1.php/");
//                HttpURLConnection http;
//                http = (HttpURLConnection) setURL.openConnection();
//                http.setDefaultUseCaches(false);
//                http.setDoInput(true);
//                http.setRequestMethod("POST");
//                http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//                StringBuffer buffer = new StringBuffer();
//                buffer.append("id").append("=").append(id);
//                OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
//                osw.write(buffer.toString());
//                osw.flush();
//                InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
//                BufferedReader reader = new BufferedReader(tmp);
//                StringBuilder builder = new StringBuilder();
//                String resultData = builder.toString();
//                String[] sResult = resultData.split("/");
//                handler1.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(ModifyInformation.this, userid + "님이 회원탈퇴되었습니다.", Toast.LENGTH_LONG).show();
//                    }
//                });
//            } catch (Exception e) {
//                Log.e("", "Error", e);
//            }
//        }).start();
//
//    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("id", id);
        outState.putString("password", password);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (requestQueue != null) {
            requestQueue.cancelAll(WithdrawalRequest.TAG);
            requestQueue.cancelAll(PasswordUpdateRequest.TAG);
        }

        super.onDestroy();
    }

}