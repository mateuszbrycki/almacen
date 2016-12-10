package com.almacen.module.share.service;

public interface ShareService {

    String getShareUrl(String[] urlPath);
    String encode(String token);
    String decode(String hash);
}
