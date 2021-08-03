package com.url.shortner.web;

import com.url.shortner.core.EncodeDecodeTinyURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortnerController {

    @Autowired
    private EncodeDecodeTinyURL encodeDecodeTinyURL;
    @PostMapping("api/v1/shorten")
    public String shortenUrl(@RequestBody  String url) {
        return encodeDecodeTinyURL.encode(url);
    }

    @GetMapping("api/v1/decode")
    public String decodeUrl(@RequestParam String code) {
        return encodeDecodeTinyURL.decode(code);
    }
}
