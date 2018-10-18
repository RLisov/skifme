package com.shaq.skifme.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBody {

    @SerializedName("user_provider_id")
    @Expose
    private String userProviderId;
    @SerializedName("provider_key")
    @Expose
    private String providerKey;
    @SerializedName("password")
    @Expose
    private String password;

    public String getUserProviderId() {
        return userProviderId;
    }

    public void setUserProviderId(String userProviderId) {
        this.userProviderId = userProviderId;
    }

    public LoginBody withUserProviderId(String userProviderId) {
        this.userProviderId = userProviderId;
        return this;
    }

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }

    public LoginBody withProviderKey(String providerKey) {
        this.providerKey = providerKey;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginBody withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "LoginBody{" +
                "userProviderId='" + userProviderId + '\'' +
                ", providerKey='" + providerKey + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
