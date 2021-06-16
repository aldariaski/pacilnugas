package com.pacilnugas.account.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class LinkCoder {
    public LinkCoder() {}

    /**
     * Encoding real data to link code.
     */
    public String encode(String unencoded, String charset) {
        try {
            String encoded = URLEncoder.encode(unencoded, charset);
            return encoded;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * Decoding link code to real data.
     */
    public String decode(String undecoded, String charset) {
        try {
            String decoded = URLDecoder.decode(undecoded, charset);
            return decoded;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
