package com.digitalsettings.feeder.tms.configuration;

import com.digitalsettings.feeder.tms.service.BearerTokenInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpClientConfig {
    @Bean
    public OkHttpClient okHttpClient(BearerTokenInterceptor bearerTokenInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(bearerTokenInterceptor)
                .build();
    }
}
