package com.example.taskplanner.auth;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static final String NEW_URL = "https://ieti-users.herokuapp.com/api/v1/auth";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("X-Been","Intercepted");

        requestBuilder.url(NEW_URL);

        Response response = chain.proceed(requestBuilder.build());
        response.newBuilder().body(response.body());

        return response;
    }
}