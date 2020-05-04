package com.seera.base;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public class RequestBodyBuilder {
    private JsonObject requestBody;

    public RequestBodyBuilder(Map<String, String> bodyFields) {
        for (String field: bodyFields.keySet()) {
            requestBody.addProperty(field,bodyFields.get(field));
        }
    }

    public void addArray(String arrayName, List<String> arr){
        requestBody.add(arrayName, new Gson().toJsonTree(arr).getAsJsonArray());
    }
}
