package com.lyle.plugin.flutter.json;

public class NamePair {
    private String camelKey;
    private String key;
    private String value;

    public NamePair(String camelKey, String key, String value) {
        this.camelKey = camelKey;
        this.key = key;
        this.value = value;
    }

    public String getCamelKey() {
        return camelKey;
    }

    public void setCamelKey(String camelKey) {
        this.camelKey = camelKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
