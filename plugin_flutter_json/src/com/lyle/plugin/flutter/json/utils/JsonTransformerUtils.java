package com.lyle.plugin.flutter.json.utils;

import com.google.gson.*;
import com.lyle.plugin.flutter.json.model.ClassModel;
import com.lyle.plugin.flutter.json.model.ParamsConfig;
import com.lyle.plugin.flutter.json.model.ParamsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JsonTransformerUtils {
    /**
     * 转换方法
     *
     * @param json      json
     * @param className class名
     * @return 转换数据
     */
    public static List<ClassModel> transformerJson(String json, String className) {
        if (json == null || !json.startsWith("{")) {
            return null;
        }
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if (jsonObject == null) {
            return null;
        }
        Set<Map.Entry<String, JsonElement>> keys = jsonObject.entrySet();
        return init(keys, className);
    }

    private static List<ClassModel> init(Set<Map.Entry<String, JsonElement>> keys, String className) {
        List<ClassModel> classModels = new ArrayList<>();
        return binaryClassList(classModels, keys, className);
    }

    private static List<ClassModel> binaryClassList(
            List<ClassModel> classModels,
            Set<Map.Entry<String, JsonElement>> keys,
            String className) {

        ClassModel classModel = new ClassModel();
        classModel.setClassName(className);
        List<ParamsModel> paramsModels = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : keys) {
            ParamsModel paramsModel = CheckParam(entry, classModels);
            paramsModels.add(paramsModel);
        }
        classModel.setParamsModels(paramsModels);
        classModels.add(classModel);
        return classModels;
    }

    private static ParamsModel CheckParam(Map.Entry<String, JsonElement> entry, List<ClassModel> classModels) {
        ParamsModel paramsModel = new ParamsModel();
        paramsModel.setName(entry.getKey());//origin name
        paramsModel.setCamelKey(StringUtils.toUpperCaseParams(entry.getKey()));
        if (entry.getValue().isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = entry.getValue().getAsJsonPrimitive();
            paramsModel.setType(getPrimitiveType(jsonPrimitive));
            paramsModel.setTypeName(getPrimitiveTypeName(jsonPrimitive, ParamsConfig.NUMString));
        } else if (entry.getValue().isJsonArray()) {
            JsonArray jsonElements = entry.getValue().getAsJsonArray();
            if (jsonElements.get(0).isJsonPrimitive()) {//普通数据数组
                paramsModel.setType(ParamsConfig.LIST);
                paramsModel.setTypeName(getPrimitiveTypeName(jsonElements.get(0).getAsJsonPrimitive(), ParamsConfig.LISTString));
            } else if (jsonElements.get(0).isJsonObject()) {//object list
                String className = Character.toUpperCase(entry.getKey().charAt(0)) + entry.getKey().substring(1) + "Bean";
                paramsModel.setType(ParamsConfig.LISTOBJECT);
                paramsModel.setTypeName(String.format(ParamsConfig.LISTString, className));
                binaryClassList(classModels, jsonElements.get(0).getAsJsonObject().entrySet(), className);
            }
        } else if (entry.getValue().isJsonObject()) {
            String className = Character.toUpperCase(entry.getKey().charAt(0)) + entry.getKey().substring(1) + "Bean";
            paramsModel.setType(ParamsConfig.OBJECT);
            paramsModel.setTypeName(className);
            binaryClassList(classModels, entry.getValue().getAsJsonObject().entrySet(), className);//类名大写
        }
        return paramsModel;
    }

    private static String getPrimitiveTypeName(JsonPrimitive jsonPrimitive, String modelType) {
        if (jsonPrimitive.isNumber()) {
            return ParamsConfig.LISTString.equals(modelType) ?
                    String.format(ParamsConfig.LISTString, ParamsConfig.NUMString) : ParamsConfig.NUMString;
        }
        if (jsonPrimitive.isString()) {
            return ParamsConfig.LISTString.equals(modelType) ?
                    String.format(ParamsConfig.LISTString, ParamsConfig.STRINGString) : ParamsConfig.STRINGString;
        }
        if (jsonPrimitive.isBoolean()) {
            return ParamsConfig.LISTString.equals(modelType) ?
                    String.format(ParamsConfig.LISTString, ParamsConfig.BOOLString) : ParamsConfig.NUMString;
        }
        return ParamsConfig.LISTString.equals(modelType) ?
                String.format(ParamsConfig.LISTString, "") : "";

    }

    private static int getPrimitiveType(JsonPrimitive jsonPrimitive) {
        if (jsonPrimitive.isNumber()) {
            return ParamsConfig.NUM;
        }
        if (jsonPrimitive.isString()) {
            return ParamsConfig.STRING;
        }
        if (jsonPrimitive.isBoolean()) {
            return ParamsConfig.BOOL;
        }
        return 0;

    }

    public static void main(String[] a) {
        List list = transformerJson(json, "test");
        int da = 1;
    }

    private static String json = "{" +
            "\t\"so_rt\": 1.2\n" +
            "}";
}

