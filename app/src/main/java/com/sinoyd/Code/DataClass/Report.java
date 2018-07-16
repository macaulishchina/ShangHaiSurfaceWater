package com.sinoyd.Code.DataClass;

import android.net.Uri;

/**
 * 作者： hyd
 * 创建时间： 2018/7/6
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */
public class Report {

    private Boolean downloaded;
    private Uri uri;
    private String name;
    private String url;

    public Boolean getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Boolean downloaded) {
        this.downloaded = downloaded;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
