package com.example.calender.requests.schedule;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ScheduleRemoveRequest extends StringRequest {
    final static private String URL = "http://192.168.123.2/delete.php";
    final static public String TAG = "ScheduleRemoveRequest";

    private final Map<String, String> map;


    public ScheduleRemoveRequest(int scheduleId,
                                 Response.Listener<String> listener,
                                 @Nullable Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("numberid", "" + scheduleId);

        setTag(TAG);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
