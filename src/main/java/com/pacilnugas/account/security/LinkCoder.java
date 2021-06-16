package com.pacilnugas.account.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LinkCoder {
    public LinkCoder() {}

    /**
     * Encoding real data to link code.
     */
    public String encode(String unencoded) {
        try {
            String encoded = URLEncoder.encode(unencoded, StandardCharsets.UTF_8.name());
            return encoded;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * Decoding link code to real data.
     */
    public String decode(String undecoded) {
        try {
            String decoded = URLDecoder.decode(undecoded, StandardCharsets.UTF_8.name());
            return decoded;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
