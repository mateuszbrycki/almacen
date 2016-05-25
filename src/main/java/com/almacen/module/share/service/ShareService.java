package com.almacen.module.share.service;

/**
 * Created by Brolly on 25.05.2016.
 */
public interface ShareService {

    String getShareUrl(String[] urlPath);
    String encode(String token);
    String decode(String hash);
}
