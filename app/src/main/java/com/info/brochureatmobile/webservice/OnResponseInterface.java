package com.info.brochureatmobile.webservice;

public interface OnResponseInterface {
    void onApiResponse(Object response);
    void onApiFailure(String message);
}
