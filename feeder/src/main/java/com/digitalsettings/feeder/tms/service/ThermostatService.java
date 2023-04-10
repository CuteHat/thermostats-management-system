package com.digitalsettings.feeder.tms.service;

import com.digitalsettings.feeder.tms.model.TemperatureDataCreateRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThermostatService {
    private final OkHttpClient okHttpClient;
    @Value("${tms.thermostats.url}")
    private String thermostatsUrl;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public List<Long> getThermostatIds() {
        Request request = new Request.Builder().url(thermostatsUrl.concat("/id")).get().build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            Type listType = new TypeToken<List<Long>>() {
            }.getType();
            Gson gson = new Gson();
            return gson.fromJson(responseBody, listType);
        } catch (IOException e) {
            throw new RuntimeException("Error while calling getThermostatIds", e);
        }
    }

    public void registerTemperatureData(Long thermostatId, TemperatureDataCreateRequest request) {
        String url = thermostatsUrl.concat("/").concat(thermostatId.toString()).concat("/temperatures");
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                .create();

        String json = gson.toJson(request);
        RequestBody body = RequestBody.create(json, JSON);
        Request httpRequest = new Request.Builder().url(url).post(body).build();

        try (Response response = okHttpClient.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while calling registerTemperatureData", e);
        }
    }

}
