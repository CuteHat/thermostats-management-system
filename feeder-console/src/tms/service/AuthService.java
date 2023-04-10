package tms.service;

import com.digitalsettings.feeder.tms.model.AuthResponse;
import com.digitalsettings.feeder.tms.model.TokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;

@Service
public class AuthService {
    @Value("${tms.user}")
    private String user;
    @Value("${tms.password}")
    private String password;
    private String accessToken = null;

    public AuthResponse syncAccessToken() {
        TokenRequest tokenRequest = new TokenRequest(user, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Auth0TokenRequestParams> entity = new HttpEntity<>(requestParams, headers);
        RestTemplate restTemplate = new RestTemplate();
    }
}
