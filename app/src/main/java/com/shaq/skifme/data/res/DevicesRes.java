package com.shaq.skifme.data.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DevicesRes {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("icon")
    @Expose
    private Icon icon;
    @SerializedName("carrier")
    @Expose
    private Carrier carrier;
    @SerializedName("terminals")
    @Expose
    private List<Terminal> terminals = new ArrayList<>();
    @SerializedName("auto_model")
    @Expose
    private String autoModel;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("seats")
    @Expose
    private int seats;
    @SerializedName("current_terminal")
    @Expose
    private CurrentTerminal currentTerminal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Terminal> terminals) {
        this.terminals = terminals;
    }

    public String getAutoModel() {
        return autoModel;
    }

    public void setAutoModel(String autoModel) {
        this.autoModel = autoModel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public CurrentTerminal getCurrentTerminal() {
        return currentTerminal;
    }

    public void setCurrentTerminal(CurrentTerminal currentTerminal) {
        this.currentTerminal = currentTerminal;
    }

    public class Carrier {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("phone1")
        @Expose
        private String phone1;

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

        public String getPhone1() {
            return phone1;
        }

        public void setPhone1(String phone1) {
            this.phone1 = phone1;
        }

    }

    public class CurrentTerminal {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("terminal_type")
        @Expose
        private TerminalType terminalType;
        @SerializedName("imei")
        @Expose
        private String imei;

        @SerializedName("auto_number_model")
        @Expose
        private String autoNumberModel;
        @SerializedName("date_from")
        @Expose
        private String dateFrom;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public TerminalType getTerminalType() {
            return terminalType;
        }

        public void setTerminalType(TerminalType terminalType) {
            this.terminalType = terminalType;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getDateFrom() {
            return dateFrom;
        }

        public void setDateFrom(String dateFrom) {
            this.dateFrom = dateFrom;
        }

        public String getAutoNumberModel() {
            return autoNumberModel;
        }

        public void setAutoNumberModel(String autoNumberModel) {
            this.autoNumberModel = autoNumberModel;
        }
    }

    public class Terminal {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("terminal_type")
        @Expose
        private TerminalType terminalType;
        @SerializedName("imei")
        @Expose
        private String imei;
        @SerializedName("date_from")
        @Expose
        private String dateFrom;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public TerminalType getTerminalType() {
            return terminalType;
        }

        public void setTerminalType(TerminalType terminalType) {
            this.terminalType = terminalType;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getDateFrom() {
            return dateFrom;
        }

        public void setDateFrom(String dateFrom) {
            this.dateFrom = dateFrom;
        }

    }

    public class Icon {

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

    public class TerminalType {

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
