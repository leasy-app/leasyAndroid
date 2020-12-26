package com.leasy.leasyAndroid.api;

import retrofit2.Response;

public interface UiCallBack {
    /**
     * Invoked when test has been send successfully
     *
     * @param response server response
     */
    void onRequestSuccessful(Response response, int code);


    /**
     * Invoked when sending has errors like 404
     *
     * @param response server response
     */
    void onRequestError(Response response, int code);


    /**
     * Invoked when sending has failure like timeout or not connected to internet
     *
     * @param t failure exception throwable
     */
    void onRequestSendFailure(Throwable t, int code);


    /**
     * when refresh token is expired -> login page must be opened
     *
     * @param response server response
     */
    void onRefreshTokenExpired(Response response, int code);

    /**
     * Invoked when obtaining new access token with current refresh token has error
     *
     * @param response server response
     */
    void onObtainAccessTokenError(Response response, int code);


    /**
     * Invoked when obtaining new access token with current refresh token has failure like internet connection problem
     *
     * @param t failure exception throwable
     */
    void onObtainAccessTokenFailure(Throwable t, int code);

    /**
     * Invoked when a 500 internal error occurs
     */
    void onInternalErrorFailure(int code);
}

