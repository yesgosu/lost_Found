package com.example.calender.requests.schedule;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FilteredFoundListRequest extends StringRequest {
    final static private String URL = "http://192.168.123.2/select.php";
    final static public String TAG = "FilteredFoundListRequest";

    private final Map<String, String> map;


    public FilteredFoundListRequest(String ga, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("ga", ga);

        setTag(TAG);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
