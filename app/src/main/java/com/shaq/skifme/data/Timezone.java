
package com.shaq.skifme.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timezone {

    @SerializedName("key")
    @Expose
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Timezone{" +
                "key='" + key + '\'' +
                '}';
    }
}
