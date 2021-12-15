package com.example.calender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.calender.model.Schedule;
import com.example.calender.requests.schedule.ScheduleRemoveRequest;
import com.example.calender.requests.schedule.ScheduleUpdateRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class scheduleForm extends AppCompatActivity {

    private static final String ARGUMENT_SCHEDULE = "ARGUMENT_SCHEDULE";
    public static final int REQUEST_CODE_DATA_CHANGE = 0x1000;

    /**
     * schedule 을 수정 또는 삭제할 때, Schedule 의 각 정보를 하나한 보내기 보단, Schedule 통째로 보내기 위한 함수
     * schedule 이 있는지 없는지에 따라, 수정/삭제인지 추가인지 알 수 있음.
     * startActivityForResult 를 하는 이유는 수정 또는 삭제한 경우 detailsCalendar 를 업데이트 해 주기 위함
     */
    public static void startActivity(Activity activity, @Nullable Schedule schedule) {
        Intent intent = new Intent(activity, scheduleForm.class);
        if (schedule != null) {
            intent.putExtra(ARGUMENT_SCHEDULE, schedule);
        }

        activity.startActivityForResult(intent, REQUEST_CODE_DATA_CHANGE);
    }


    private final String TAG = "ScheduleForm";

    private Schedule schedule = null;
    private RequestQueue requestQueue;

    private EditText edtitle, edcontent;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 수정 또는 삭제의 경우, 반드시 intent 에 schedule 이 포함되어야 한다.
        // 즉, schedule 의 데이터 유무 판단으로 수정인지, 새로 작성하는 것인지 알 수 있음.
        if (getIntent() != null) {
            schedule = getIntent().getParcelableExtra(ARGUMENT_SCHEDULE);
        }

        if (schedule == null && savedInstanceState != null) {
            schedule = savedInstanceState.getParcelable(ARGUMENT_SCHEDULE);
        }

        setContentView(R.layout.activity_schedule_form);

        requestQueue = Volley.newRequestQueue(this);

        initUi();
    }

    private void initUi() {
        edtitle = findViewById(R.id.edtitle);
        edcontent = findViewById(R.id.edcontent);
        spinner = findViewById(R.id.formspinner);

        ArrayAdapter<CharSequence> qaAdapter = ArrayAdapter.createFromResource(this, R.array.ga, android.R.layout.simple_spinner_dropdown_item);
        qaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(qaAdapter);

        Button buttonConfirm = findViewById(R.id.buttonplus);
        buttonConfirm.setOnClickListener(v -> {
            String ga = spinner.getSelectedItem().toString();
            String title = edtitle.getText().toString();
            String content = edcontent.getText().toString();

            if (schedule == null) {
                insertSchedule(ga, title, content);

            } else {
                updateSchedule(ga, title, content);
            }
        });

        Button buttonCancel = findViewById(R.id.buttondelete);
        buttonCancel.setOnClickListener(v -> finish());

        if (schedule != null) {
            List<String> gaList = Arrays.asList(getResources().getStringArray(R.array.ga));
            int index = gaList.indexOf(schedule.ga);
            if (index < 0) {
                index = 0;
            }

            spinner.setSelection(index);
            edtitle.setText(schedule.title);
            edcontent.setText(schedule.content);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (schedule != null) {
            getMenuInflater().inflate(R.menu.menu_schedule_edit, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("글 삭제")
                .setMessage("정말로 글을 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> deleteSchedule())
                .setNegativeButton("취소", (dialog, which) -> dialog.dismiss())
                .show();

        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (schedule != null) {
            outState.putParcelable(ARGUMENT_SCHEDULE, schedule);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        // 진행중인 request 가 있다면 모두 취소한다.
        requestQueue.cancelAll(ScheduleRemoveRequest.TAG);
        requestQueue.cancelAll(ScheduleUpdateRequest.TAG);

        super.onDestroy();
    }

    private void insertSchedule(final String ga, final String title, final String content) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("ga", ga);
        bodyMap.put("title", title);
        bodyMap.put("content", content);

        postRequest("Http://192.168.123.2/insert1.php/", bodyMap);
    }

    private void updateSchedule(final String ga, final String title, final String content) {
        if (schedule == null) return;

        requestQueue.cancelAll(ScheduleUpdateRequest.TAG);

        requestQueue.add(new ScheduleUpdateRequest(
                schedule.numberId,
                ga,
                title,
                content,
                response -> {
                    Log.d(TAG, response);

                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getBoolean("success")) {
                            Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
                            setResult(Activity.RESULT_OK);
                            finish();

                        } else {
                            showErrorToastMessage();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showErrorToastMessage();
                    }
                },
                error -> {
                    error.printStackTrace();
                    showErrorToastMessage();
                })
        );
    }

    private void deleteSchedule() {
        if (schedule == null) return;

        requestQueue.cancelAll(ScheduleRemoveRequest.TAG);

        requestQueue.add(new ScheduleRemoveRequest(
                schedule.numberId,
                response -> {
                    Log.d(TAG, response);

                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getBoolean("success")) {
                            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            setResult(Activity.RESULT_OK);
                            finish();

                        } else {
                            showErrorToastMessage();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showErrorToastMessage();
                    }
                },
                error -> {
                    error.printStackTrace();
                    showErrorToastMessage();
                })
        );
    }

    private void showErrorToastMessage() {
        Toast.makeText(this, "오류가 발생하였습니다. 잠시 후 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
    }

    private void postRequest(String url, Map<String, Object> bodyData) {
        new Thread(() -> {
            try {
                URL setURL = new URL(url);
                HttpURLConnection http;
                http = (HttpURLConnection) setURL.openConnection();
                http.setDefaultUseCaches(false);
                http.setDoInput(true);
                http.setRequestMethod("POST");
                http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
                StringBuilder bodyBuilder = new StringBuilder();
                for (String key : bodyData.keySet()) {
                    bodyBuilder.append(key).append("=").append(bodyData.get(key)).append("&");
                }
                if (bodyBuilder.length() > 0) {
                    bodyBuilder.deleteCharAt(bodyBuilder.length() - 1);
                }
                outStream.write(bodyBuilder.toString());
                outStream.flush();
                InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
                final BufferedReader reader = new BufferedReader(tmp);

                String str;
                while ((str = reader.readLine()) != null) {
                    System.out.println(str);
                }

                finish();

            } catch (Exception e) {
                Log.e("dataInsert()", "지정 에러 발생", e);
            }
        }).start();
    }
}



