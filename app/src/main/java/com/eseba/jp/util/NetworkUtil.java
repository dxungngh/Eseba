package com.eseba.jp.util;

import android.content.Context;

import com.eseba.jp.Config;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by danielnguyen on 9/22/17.
 */

public class NetworkUtil {
    private static Picasso picasso;

    public static Picasso getPicasso(Context context) {
        if (picasso == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                            .addHeader(
                                Config.Network.AUTHORIZATION,
                                Config.Network.AUTHORIZATION_CODE
                            )
                            .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

            picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
        }
        return picasso;
    }
}
