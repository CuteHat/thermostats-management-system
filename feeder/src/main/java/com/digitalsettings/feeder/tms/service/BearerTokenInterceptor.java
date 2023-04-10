package com.digitalsettings.feeder.tms.service;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class BearerTokenInterceptor implements Interceptor {
    private final AuthService authService;

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request originalRequest = chain.request();
        String accessToken = authService.getAccessToken();
        okhttp3.Request requestWithBearerToken = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + accessToken)
                .build();

        Response response = chain.proceed(requestWithBearerToken);

        if (response.code() == 401) {
            authService.syncAccessToken();
            accessToken = authService.getAccessToken();
            requestWithBearerToken = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
            response = chain.proceed(requestWithBearerToken);
        }

        return response;
    }
}
