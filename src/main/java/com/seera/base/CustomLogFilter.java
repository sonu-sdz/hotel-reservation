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
        requestBuilder.append(requestSpec.getMethod());
        requestBuilder.append("\n");
        requestBuilder.append(requestSpec.getURI());
        requestBuilder.append("\n");
        requestBuilder.append("*************");
        responseBuilder = new StringBuilder();
        responseBuilder.append(response.getStatusLine());
        responseBuilder.append("\n");
        responseBuilder.append(response.getBody().print());
        return response;
    }



}
