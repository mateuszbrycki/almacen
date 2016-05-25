package com.almacen.module.share.service;


import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service("shareService")
public class ShareServiceImpl implements ShareService {
    private String hash = "#ALMShare";

    @Override
    public String getShareUrl(String[] urlPath) {
        String url = "";
        for (int i = 0; i < urlPath.length; i++) {
            if (i == 0)
                url += urlPath[i] + "//";
            else
                url += urlPath[i];
        }
        return url;

    }

    @Override
    public String encode(String token) {
        token = hash + token;
        byte[] encodedBytes = Base64.encode(token.getBytes());
        return new String(encodedBytes, Charset.forName("UTF-8"));
    }

    @Override
    public String decode(String hash) {
        byte[] decodedBytes = Base64.decode(hash.getBytes());
        String decodedHash = new String(decodedBytes, Charset.forName("UTF-8"));
        return decodedHash.replace(hash, "");
    }
}
