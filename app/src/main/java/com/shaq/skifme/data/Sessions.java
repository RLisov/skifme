package com.shaq.skifme.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sessions {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_module")
    @Expose
    private String idModule;
    @SerializedName("module_name")
    @Expose
    private String moduleName;
    @SerializedName("key_application")
    @Expose
    private String keyApplication;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getKeyApplication() {
        return keyApplication;
    }

    public void setKeyApplication(String keyApplication) {
        this.keyApplication = keyApplication;
    }
}
