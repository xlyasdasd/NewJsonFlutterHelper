package com.lyle.plugin.flutter.json.model;

import java.util.List;

public class ClassModel {
    private String className;
    private List<ParamsModel> paramsModels;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ParamsModel> getParamsModels() {
        return paramsModels;
    }

    public void setParamsModels(List<ParamsModel> paramsModels) {
        this.paramsModels = paramsModels;
    }
}
