package com.example.calender.requests.schedule;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ScheduleUpdateRequest extends StringRequest {
    final static private String URL = "http://192.168.123.2/update.php";
    final static public String TAG = "ScheduleUpdateRequest";

    private final Map<String, String> map;


    public ScheduleUpdateRequest(int scheduleId,
                                 String ga,
                                 String title,
                                 String content,
                                 Response.Listener<String> listener,
                                 @Nullable Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("numberid", "" + scheduleId);
        map.put("ga", ga);
        map.put("title", title);
        map.put("content", content);

        setTag(TAG);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
