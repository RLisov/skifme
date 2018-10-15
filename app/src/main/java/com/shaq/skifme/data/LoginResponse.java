package com.shaq.skifme.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("code")
    @Expose
    public String code;
}
