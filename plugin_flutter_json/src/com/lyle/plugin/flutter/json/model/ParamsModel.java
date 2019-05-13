package com.lyle.plugin.flutter.json.model;

public class ParamsModel {
    private int type;//参数属性
    private String name;//参数原始名 用于json交互
    private String camelKey;//驼峰名
    private String typeName;//参数属性名

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamelKey() {
        return camelKey;
    }

    public void setCamelKey(String camelKey) {
        this.camelKey = camelKey;
    }
}
