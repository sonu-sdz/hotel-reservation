package com.seera.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.seera.base.BaseAPI;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseSteps {

    BaseAPI baseAPI = new BaseAPI();
    static JsonObject requestBody;

    @Given("^testing environment is set$")
    public void setEnvironment() {
        baseAPI.setBaseURI("https://www.tajawal.ae");
    }

    @When("^I set headers as below$")
    public void setHeaders(Map<String, String> headers) {
        baseAPI.setHeaders(headers);
    }

    @And("^I pass query parameters as below$")
    public void setQueryParams(Map<String, String> params) {
        baseAPI.setQueryParams(params);
    }

    @And("I invoke GET request on resource {string}")
    public void invokeGET(String resource) {
        baseAPI.invokeGET(resource);
    }

    @And("I invoke POST request on resource {string}")
    public void invokePOST(String resource) {
        baseAPI.invokePOST(resource);
    }

    @And("I get response status as {string}")
    public void assertResponseCode(String expected) {
        Assert.assertEquals(expected, String.valueOf(baseAPI.getResponseStatusCode()));
    }

    @And("response body contains")
    public void assertResponseBody(List<String> expected) {
        assertResponseBodyNotNull();
        String responseBody = baseAPI.getResponseBody();
        for (String item : expected) {
            Assert.assertTrue("does not contain " + item + "in " + responseBody, responseBody.toUpperCase().contains(item.toUpperCase()));
        }
    }

    @And("response body is not null")
    public void assertResponseBodyNotNull() {
        Assert.assertNotNull(baseAPI.getResponseBody());
    }

    @And("I add an object {string} with below fields to request body")
    public void addObjectToRequestBody(String field, Map<String, String> objectFields) {
        baseAPI.addToBody(field, objectFields);
    }

    @And("I add below fields to request body")
    public void addFieldsToRequestBody(Map<String, String> fields) {
        baseAPI.addToBody(fields);
    }

    @And("I add a room array item as below to request body")
    public void addRoom(DataTable tb) {
        List<Map<String, String>> fields = tb.asMaps(String.class, String.class);
        JsonObject bodyObject = baseAPI.getBodyAsJsonObject();
        JsonArray roomArr = bodyObject.get("room") == null ? new JsonArray() : bodyObject.get("room").getAsJsonArray();
        JsonArray guestArr = new JsonArray();
        for (Map<String, String> field : fields) {
            JsonObject guestObj = new JsonObject();
            for (String key : field.keySet()) {
                guestObj.addProperty(key, field.get(key));
            }
            guestArr.add(guestObj);
        }
        JsonObject room = new JsonObject();
        room.add("guest", guestArr);
        roomArr.add(room);
        baseAPI.setBody(bodyObject.toString());
    }

}
