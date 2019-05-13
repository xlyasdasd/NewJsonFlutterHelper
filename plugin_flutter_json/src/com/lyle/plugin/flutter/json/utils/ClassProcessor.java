package com.lyle.plugin.flutter.json.utils;

import com.lyle.plugin.flutter.json.model.ClassModel;
import com.lyle.plugin.flutter.json.model.ParamsConfig;
import com.lyle.plugin.flutter.json.model.ParamsModel;

import java.util.Collections;
import java.util.List;


public class ClassProcessor {
    /**
     * build class string
     *
     * @param classModels Class实体类List
     * @return class string
     */
    public static String buildClass(List<ClassModel> classModels) {
        Collections.reverse(classModels);
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
        stringBuilder.append(String.format(ClassCommand.CONSTRUCT_FRONT, classModel.getClassName()));
        for (ParamsModel paramsModel : classModel.getParamsModels()) {
            stringBuilder.append(String.format(ClassCommand.CONSTRUCT_PARAMS, paramsModel.getCamelKey()));
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(ClassCommand.CONSTRUCT_END);
        stringBuilder.append(ClassCommand.ENTER);//额外换行
        stringBuilder.append(String.format(ClassCommand.FROM_JSON_FRONT, classModel.getClassName()));
        for (ParamsModel paramsModel : classModel.getParamsModels()) {
            if (paramsModel.getType() == ParamsConfig.LISTOBJECT) {//list object
                stringBuilder.append(String.format(ClassCommand.FROM_JSON_LIST_OBJECT_JSON_INPUT, paramsModel.getCamelKey(), paramsModel.getName(), paramsModel.getName(), StringUtils.toUpperCaseFirstOne(paramsModel.getCamelKey() + "Bean")));
                continue;
            }
            if (paramsModel.getType() == ParamsConfig.LIST) {//list pri
                stringBuilder.append(String.format(ClassCommand.FROM_JSON_LIST_PRI_JSON_INPUT, paramsModel.getCamelKey(), paramsModel.getName(), paramsModel.getTypeName().replace("List<", "").replace(">", "")));
                continue;
            }
            if (paramsModel.getType() == ParamsConfig.OBJECT) {//obj
                stringBuilder.append(String.format(ClassCommand.FROM_JSON_OBJ_INPUT, paramsModel.getCamelKey(), paramsModel.getName(), StringUtils.toUpperCaseFirstOne(paramsModel.getCamelKey() + "Bean")));
                continue;
            }
            stringBuilder.append(String.format(ClassCommand.FROM_JSON_INPUT, paramsModel.getCamelKey(), paramsModel.getName()));
        }
        stringBuilder.append(ClassCommand.SIMPLY_TAB_FUN_END);
        stringBuilder.append(ClassCommand.ENTER);//额外换行
        stringBuilder.append(ClassCommand.TO_JSON_FRONT);
        for (ParamsModel paramsModel : classModel.getParamsModels()) {
            if (paramsModel.getType() == ParamsConfig.LISTOBJECT) {//list object
                stringBuilder.append(String.format(ClassCommand.TO_JSON_LIST_OBJECT_INPUT, paramsModel.getName(), paramsModel.getCamelKey()));
                continue;
            }
            if (paramsModel.getType() == ParamsConfig.LIST) {//list pri
                stringBuilder.append(String.format(ClassCommand.TO_JSON_LIST_PRI_JSON_INPUT, paramsModel.getName(), paramsModel.getCamelKey()));
                continue;
            }
            if (paramsModel.getType() == ParamsConfig.OBJECT) {//obj
                stringBuilder.append(String.format(ClassCommand.TO_JSON_OBJECT_INPUT, paramsModel.getCamelKey(), paramsModel.getName()));
                continue;
            }
            stringBuilder.append(String.format(ClassCommand.TO_JSON_INPUT, paramsModel.getName(), paramsModel.getCamelKey()));
        }
        stringBuilder.append(ClassCommand.TO_JSON_END);
        stringBuilder.append(ClassCommand.ENTER);//额外换行
        stringBuilder.append(ClassCommand.SIMPLY_FUN_END);//额外换行
        stringBuilder.append(ClassCommand.ENTER);//额外换行
    }

    private static String buildParams(ParamsModel paramsModel) {
        return String.format(ClassCommand.PARAMS, paramsModel.getTypeName(), paramsModel.getCamelKey());
    }

    private static String classTitle(ClassModel classModel) {
        return String.format(ClassCommand.CLASS_TITLE, classModel.getClassName());
    }

}
