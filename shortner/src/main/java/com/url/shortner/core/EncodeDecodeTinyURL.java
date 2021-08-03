package com.url.shortner.core;


import com.url.shortner.repository.URLRepository;
import com.url.shortner.repository.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;


/**
 * Auxiliary class
 * 
 * Runtime: 1 ms, faster than 98.40% of Java online submissions.
 * Memory Usage: 39 MB, less than 83.80% of Java online submissions.
 */
@Component
public  class EncodeDecodeTinyURL {

    @Autowired
    URLRepository urlRepository;
    // **** Codec class member(s) ****
    HashMap<String, String> hm = null;
    char map[] = null;

    /**
     * Constructor.
     */
    public EncodeDecodeTinyURL() {
        this.hm     = new HashMap<>();
        this.map    = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    }


    /**
     * Encodes a URL to a shortened URL.
     * Uses a base 62 encoding.
     * O(n) 
     */
    public String encode(String longUrl) {
        
        // sanity check(s)
        if (longUrl.equals(""))
            return "";

        // initialization
        StringBuilder sb    = new StringBuilder();
        int sum             = 0;

        // sum characters in string - O(n)
        for (char c : longUrl.toCharArray())
            sum += c;

        //convert the sum to a base 62 number
        while (sum > 0) {
            sb.append(map[sum % 62]); 
            sum /= 62;  
        }

        // **** string builder to string ****
        String shortUrl = sb.toString();
        this.hm.put(shortUrl, longUrl);
        Url url = new Url();
        url.setUrl(longUrl);
        url.setId(shortUrl);
        if(urlRepository.count() > 10_000_000) {
            Url urlToDel = urlRepository.findFirstByOrderByCreatedDesc();
            urlRepository.deleteById(urlToDel.getId());
        }
        urlRepository.save(url);
        return shortUrl;
    }


    /**
     * Decodes a shortened URL to its original URL.
     */
    public String decode(String shortUrl) {

        //sanity check
        if (shortUrl.equals(""))
            return "";

        // return longUrl
        String url = "NOT_FOUND";
        try {
            url = urlRepository.getById(shortUrl).getUrl();
        } catch (EntityNotFoundException entityNotFoundException) {

        }

        return url;
    }
}
