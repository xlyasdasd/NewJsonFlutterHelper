package com.lyle.plugin.flutter.json.utils;

import com.lyle.plugin.flutter.json.model.ClassModel;
import com.lyle.plugin.flutter.json.model.ParamsModel;

import java.util.List;


public class ClassProcessor {

    public static String buildClass(List<ClassModel> classModels) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < classModels.size(); i++) {
            buildClassParam(stringBuilder, classModels.get(i));
        }
        return stringBuilder.toString();
    }

    private static void buildClassParam(StringBuilder stringBuilder, ClassModel classModel) {
        stringBuilder.append(classTitle(classModel));
        for (ParamsModel paramsModel : classModel.getParamsModels()) {
            stringBuilder.append(buildParams(paramsModel));
        }
        stringBuilder.append(ClassCommand.ENTER);//额外换行
        stringBuilder.append(ClassCommand.CONSTRUCT_FRONT);
        for (ParamsModel paramsModel : classModel.getParamsModels()) {
            stringBuilder.append(String.format(ClassCommand.CONSTRUCT_PARAMS, paramsModel.getCamelKey()));
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append(ClassCommand.CONSTRUCT_END);
        stringBuilder.append(ClassCommand.ENTER);//额外换行
        stringBuilder.append(ClassCommand.FROM_JSON_FRONT);
        for (ParamsModel paramsModel : classModel.getParamsModels()) {
            stringBuilder.append(String.format(ClassCommand.FROM_JSON_INPUT, paramsModel.getCamelKey(), paramsModel.getName()));
        }
        stringBuilder.append(ClassCommand.SIMPLY_TAB_FUN_END);
        stringBuilder.append(ClassCommand.ENTER);//额外换行
        stringBuilder.append(ClassCommand.TO_JSON_FRONT);
        stringBuilder.append(ClassCommand.TO_JSON_INPUT);

    }

    private static String buildParams(ParamsModel paramsModel) {
        return String.format(ClassCommand.PARAMS, paramsModel.getTypeName(), paramsModel.getCamelKey());
    }

    private static String classTitle(ClassModel classModel) {
        return String.format(ClassCommand.CLASS_TITLE, classModel.getClassName());
    }

    String classesString;

}
