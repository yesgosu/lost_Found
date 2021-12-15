package com.example.calender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.calender.model.Schedule;
import com.example.calender.requests.schedule.FilteredFoundListRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class detailsCalender extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    private final String TAG = "DetailsCalendar";

    private String id =null;
    private String password = null;

    private RequestQueue requestQueue;

    private Spinner spinner;
    private SwipeRefreshLayout refreshLayout;

    private ScheduleAdapter adapter;


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

        setContentView(R.layout.activity_details_calender);

        requestQueue = Volley.newRequestQueue(this);

        initUi();
    }

    private void initUi() {
        setSupportActionBar(findViewById(R.id.toolbar));

        Button btnplus1 = findViewById(R.id.btnplus1);
        Button btnmy = findViewById(R.id.btnmy);

        RecyclerView rv = findViewById(R.id.rv);
        {
            rv.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ScheduleAdapter(s -> scheduleForm.startActivity(this, s));

            rv.setAdapter(adapter);
        }

        refreshLayout = findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setOnRefreshListener(this::loadItems);

        spinner = findViewById(R.id.spinner);
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ga, R.layout.item_main_spinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    loadItems();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        btnplus1.setOnClickListener(v -> {
            scheduleForm.startActivity(this, null);
        });

        btnmy.setOnClickListener(view -> {
            Intent intent = new Intent(detailsCalender.this, ModifyInformation.class);
            intent.putExtra("id", id);
            intent.putExtra("password", password);
            startActivityForResult(intent, 0);
        });
    }

    private void loadItems() {
        String filter = (String) spinner.getSelectedItem();
        Log.d(TAG, filter);

        requestQueue.cancelAll(FilteredFoundListRequest.TAG);
        requestQueue.add(new FilteredFoundListRequest(filter, this, this));
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG, response);

        try {
            ArrayList<Schedule> schedules = new ArrayList<>();

            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("result");

            for (int i = 0; i < array.length(); i++) {
                schedules.add(new Schedule(array.getJSONObject(i)));
            }

            adapter.submitList(schedules);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();

        refreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        requestQueue.cancelAll(FilteredFoundListRequest.TAG);

        super.onDestroy();
    }

    protected void onActivityResult(@NonNull Bundle outState, int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        outState.putString("id", id);
        outState.putString("password", password);
        // 데이터 추가, 삭제, 수정한 경우 목록 refresh
        if (requestCode == scheduleForm.REQUEST_CODE_DATA_CHANGE && resultCode == Activity.RESULT_OK) {
            loadItems();
        }
    }
}