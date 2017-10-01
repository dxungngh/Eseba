package com.eseba.jp.listener;

public interface GenericListener<T> {
    public void onSuccess(T result);

    public void onFailed(Throwable error);
}