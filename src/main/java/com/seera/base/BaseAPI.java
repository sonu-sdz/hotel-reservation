package com.seera.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BaseAPI {

    RequestSpecBuilder requestSpecBuilder;
    RequestSpecification requestSpecification;
    Response response;

    public BaseAPI() {
        requestSpecBuilder = new RequestSpecBuilder();
    }

    public void setHeaders(Map<String,String> headerMap){
        requestSpecBuilder.addHeaders(headerMap);
    }

    public void setBaseURI(String baseURI){
        requestSpecBuilder.setBaseUri(baseURI);
    }

    public String getBaseURI() {
        return RestAssured.baseURI;
    }

    public void setPathParams(Map<String, String> params){
        requestSpecBuilder.addPathParams(params);
    }

    public void setQueryParams(Map<String, String> params){
        requestSpecBuilder.addQueryParams(params);
    }

    public void setBody(Object body){
        requestSpecBuilder.setBody(body);
    }

    public void invokePOST(String resource){
        setResponse(buildRequestSpec().post(resource));
    }

    public void invokeGET(String resource){
        setResponse(buildRequestSpec().get(resource));
    }

    public void invokePUT(String resource){
        setResponse(buildRequestSpec().put(resource));
    }

    private RequestSpecification buildRequestSpec(){
        setRequestSpecification(requestSpecBuilder.addFilter(new CustomLogFilter()).build());
        return getRequestSpecification();
    }

    private void setResponse(Response response){
        this.response = response;
    }

    private Response getResponse(){
        return this.response;
    }

    public int getResponseStatusCode(){
        return getResponse().getStatusCode();
    }

    public String getResponseBody(){
        return getResponse().getBody().asString();
    }

    private RequestSpecification getRequestSpecification() {
        return RestAssured.given(this.requestSpecification);
    }

    private void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }
}
