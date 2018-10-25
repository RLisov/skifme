package com.shaq.skifme.data.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoMe {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_approved")
    @Expose
    private boolean emailApproved;
    @SerializedName("lang")
    @Expose
    private Lang lang;
    @SerializedName("timezone")
    @Expose
    private Timezone timezone;
    @SerializedName("photos")
    @Expose
    private List<Object> photos = null;
    @SerializedName("companies")
    @Expose
    private List<Company> companies = null;
    @SerializedName("role")
    @Expose
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailApproved() {
        return emailApproved;
    }

    public void setEmailApproved(boolean emailApproved) {
        this.emailApproved = emailApproved;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public List<Object> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Object> photos) {
        this.photos = photos;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public class Company {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("module")
        @Expose
        private String module;
        @SerializedName("timezone")
        @Expose
        private Timezone_ timezone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public Timezone_ getTimezone() {
            return timezone;
        }

        public void setTimezone(Timezone_ timezone) {
            this.timezone = timezone;
        }

    }

    public class Lang {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("value_ru")
        @Expose
        private String valueRu;
        @SerializedName("value_en")
        @Expose
        private String valueEn;
        @SerializedName("value_kz")
        @Expose
        private String valueKz;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValueRu() {
            return valueRu;
        }

        public void setValueRu(String valueRu) {
            this.valueRu = valueRu;
        }

        public String getValueEn() {
            return valueEn;
        }

        public void setValueEn(String valueEn) {
            this.valueEn = valueEn;
        }

        public String getValueKz() {
            return valueKz;
        }

        public void setValueKz(String valueKz) {
            this.valueKz = valueKz;
        }

    }

    public class Timezone {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("value_ru")
        @Expose
        private String valueRu;
        @SerializedName("value_en")
        @Expose
        private String valueEn;
        @SerializedName("value_kz")
        @Expose
        private String valueKz;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValueRu() {
            return valueRu;
        }

        public void setValueRu(String valueRu) {
            this.valueRu = valueRu;
        }

        public String getValueEn() {
            return valueEn;
        }

        public void setValueEn(String valueEn) {
            this.valueEn = valueEn;
        }

        public String getValueKz() {
            return valueKz;
        }

        public void setValueKz(String valueKz) {
            this.valueKz = valueKz;
        }

    }

    public class Timezone_ {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("value_ru")
        @Expose
        private String valueRu;
        @SerializedName("value_en")
        @Expose
        private String valueEn;
        @SerializedName("value_kz")
        @Expose
        private String valueKz;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValueRu() {
            return valueRu;
        }

        public void setValueRu(String valueRu) {
            this.valueRu = valueRu;
        }

        public String getValueEn() {
            return valueEn;
        }

        public void setValueEn(String valueEn) {
            this.valueEn = valueEn;
        }

        public String getValueKz() {
            return valueKz;
        }

        public void setValueKz(String valueKz) {
            this.valueKz = valueKz;
        }

    }
}
