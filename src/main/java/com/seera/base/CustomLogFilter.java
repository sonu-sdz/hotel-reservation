package com.seera.base;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

public class CustomLogFilter implements Filter {

    private static StringBuilder requestBuilder;
    private static StringBuilder responseBuilder;

    public static StringBuilder getResponseBuilder() {
        return responseBuilder;
    }

    public static StringBuilder getRequestBuilder() {
        return requestBuilder;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        requestBuilder = new StringBuilder();
        requestBuilder.append("*************");
        requestBuilder.append("\n");
        requestBuilder.append("Method: " + requestSpec.getMethod());
        requestBuilder.append("\n");
        requestBuilder.append("URI: " + requestSpec.getURI());
        requestBuilder.append("\n");
        System.out.println(requestBuilder.toString());
        responseBuilder = new StringBuilder();
        responseBuilder.append("*******RESPONSE******");
        responseBuilder.append(response.getStatusLine());
        responseBuilder.append("\n");
        responseBuilder.append(response.getBody().print());
        requestBuilder.append("*************");
        //System.out.println(responseBuilder.toString());
        return response;
    }



}
