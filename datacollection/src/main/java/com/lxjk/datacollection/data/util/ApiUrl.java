package com.lxjk.datacollection.data.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: ziv
 * @Date: 2018/5/21 08:33
 * @Description: 接收数据接口配置
 */
@Component
public class ApiUrl {

    private static String datarecerveUrl;
    private static String apiURL;
    private static String loginURL;
    private static String api_sux = "/api";
    private static String login_sux = "/login";
    private static String api_uploadData = "/test/jsonUpload";

    @Value("${api.datarecerve-url}")
    private void setDatarecerveUrl(String datarecerveUrl) {
        ApiUrl.datarecerveUrl = datarecerveUrl;
    }

    public String getDatarecerveUrl() {
        return datarecerveUrl;
    }

    public String getApiURL() {
        setApiURL(api_sux);
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        ApiUrl.apiURL = datarecerveUrl + apiURL;
    }

    public String getLoginURL() {
        setLoginURL(login_sux);
        return loginURL;
    }

    public void setLoginURL(String loginURL) {
        ApiUrl.loginURL = datarecerveUrl + loginURL;
    }

    public String getApi_uploadData() {
//        setApi_uploadData(api_uploadData);
        return datarecerveUrl + api_uploadData;
    }

    public void setApi_uploadData(String api_uploadData) {
        ApiUrl.api_uploadData =  api_uploadData;
    }
}
