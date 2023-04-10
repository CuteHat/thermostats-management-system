package com.digitalsettings.feeder.tms.service;

import com.digitalsettings.feeder.tms.model.AuthResponse;
import com.digitalsettings.feeder.tms.model.TokenRequest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    @Value("${tms.user}")
    private String user;
    @Value("${tms.password}")
    private String password;
    @Value("${tms.auth.url}")
    private String authUrl;
    private String accessToken = null;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void syncAccessToken() {
        TokenRequest tokenRequest = new TokenRequest(user, password);

        Gson gson = new Gson();
        String json = gson.toJson(tokenRequest);

        OkHttpClient client = new OkHttpClient.Builder().build();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(authUrl.concat("/token")).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            AuthResponse authResponse = gson.fromJson(responseBody, AuthResponse.class);
            this.accessToken = authResponse.getAccessToken();
        } catch (Exception e) {
            log.error("Error while getting access token", e);
            throw new RuntimeException(e);
        }
    }

    public String getAccessToken() {
        if (accessToken == null) {
            syncAccessToken();
        }
        return accessToken;
    }
}
