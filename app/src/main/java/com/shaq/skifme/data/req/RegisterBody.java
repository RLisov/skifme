
package com.shaq.skifme.data.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shaq.skifme.data.Language;
import com.shaq.skifme.data.Timezone;

public class RegisterBody {

    @SerializedName("user_provider_id")
    @Expose
    private String userProviderId;
    @SerializedName("provider_key")
    @Expose
    private String providerKey;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("timezone")
    @Expose
    private Timezone timezone;
    @SerializedName("language")
    @Expose
    private Language language;

    public String getUserProviderId() {
        return userProviderId;
    }

    public void setUserProviderId(String userProviderId) {
        this.userProviderId = userProviderId;
    }

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "RegisterBody{" +
                "userProviderId='" + userProviderId + '\'' +
                ", providerKey='" + providerKey + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", timezone=" + timezone +
                ", language=" + language +
                '}';
    }
}
