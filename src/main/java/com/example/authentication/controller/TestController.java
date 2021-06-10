package com.example.authentication.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Value("${MERCADOTOKEN}")
    private String TOKEN;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @Secured("MODERATOR")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/admin/subscriptions")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String subscriptions(@RequestParam String email) throws URISyntaxException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        URI uri = new URIBuilder("https://api.mercadopago.com/preapproval/search?payer_email=" + email).build();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Authorization", "BEARER " + TOKEN);
        CloseableHttpResponse response = client.execute(httpGet);

        return EntityUtils.toString(response.getEntity());
    }
}
