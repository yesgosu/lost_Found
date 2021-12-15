package com.example.calender.requests.auth;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PasswordUpdateRequest extends StringRequest {
    final static private String URL = "http://192.168.123.2/update_member.php";
    final static public String TAG = "PasswordUpdateRequest";

    private final Map<String, String> map;


    public PasswordUpdateRequest(String id, String newPassword, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("id", id);
        map.put("new_password", newPassword);

        setTag(TAG);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
