package com.example.demo.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class Sorting {

    public static JSONArray sortJSONArray(JSONArray array, String sortBy) throws JSONException {
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getJSONObject(i));
        }
        list.sort((a1, a2) -> {
            try {
                return a1.getString(sortBy).compareTo(a2.getString(sortBy));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        });
        return new JSONArray(list);
    }
}
