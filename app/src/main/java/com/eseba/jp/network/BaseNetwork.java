package com.eseba.jp.network;

import com.eseba.jp.Config;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class BaseNetwork {
    protected String getUrl(String url) {
        return Config.Network.HOST + url;
    }
}
