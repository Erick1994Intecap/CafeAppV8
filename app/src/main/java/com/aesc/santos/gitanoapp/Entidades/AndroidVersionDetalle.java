package com.aesc.santos.gitanoapp.Entidades;

import java.io.Serializable;

/**
 * Created by Android on 7/02/2018.
 */

public class AndroidVersionDetalle implements Serializable {
    public String android_version_name;
    public String android_version_desc;
    public String android_image_url;
//Android


    public AndroidVersionDetalle(String android_version_name, String android_version_desc, String android_image_url) {
        this.android_version_name = android_version_name;
        this.android_version_desc = android_version_desc;
        this.android_image_url = android_image_url;
    }

    public String getAndroid_version_name() {
        return android_version_name;
    }

    public void setAndroid_version_name(String android_version_name) {
        this.android_version_name = android_version_name;
    }

    public String getAndroid_version_desc() {
        return android_version_desc;
    }

    public void setAndroid_version_desc(String android_version_desc) {
        this.android_version_desc = android_version_desc;
    }

    public String getAndroid_image_url() {
        return android_image_url;
    }

    public void setAndroid_image_url(String android_image_url) {
        this.android_image_url = android_image_url;
    }

    @Override
    public String toString() {
        return "AndroidVersionDetalle{" +
                "android_version_name='" + android_version_name + '\'' +
                ", android_version_desc='" + android_version_desc + '\'' +
                ", android_image_url='" + android_image_url + '\'' +
                '}';
    }
}
