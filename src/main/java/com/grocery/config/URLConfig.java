package com.grocery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class URLConfig {

    public Map getURLS() {
        Map<String, List<String>> role = new HashMap<>();
        // add new URL's with comma
        String[] adminUrlArray = {"/grocery/product/**"};
        String[] userUrlArray = {"/grocery/user/**", "/grocery/carts/**", "/grocery/orders/**", "/grocery/product/get/**"};
        String[] publicUrlArray = {"/grocery/auth/**"};

        // Convert to List using Arrays.asList
        List<String> adurls = Arrays.asList(adminUrlArray);
        List<String> userurls = Arrays.asList(userUrlArray);
        List<String> publicurls = Arrays.asList(publicUrlArray);

        role.put("ADMIN", adurls);
        role.put("USER", userurls);
        role.put("PUBLIC", publicurls);

        return role;
    }
}
