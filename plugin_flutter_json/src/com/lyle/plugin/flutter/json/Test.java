package com.lyle.plugin.flutter.json;

import com.lyle.plugin.flutter.json.utils.ClassProcessor;
import com.lyle.plugin.flutter.json.utils.JsonTransformerUtils;

import static com.lyle.plugin.flutter.json.utils.StringUtils.toUpperCaseFirstOne;
import static com.lyle.plugin.flutter.json.utils.StringUtils.toUpperCaseParams;

public class Test {
    public static void main(String[] arg){
        String a = ClassProcessor.buildClass(JsonTransformerUtils.transformerJson(ss, "Test"));
        int b = 1;
    }
    public static String ss  = "{\n" +
            "\t\"int\": 1,\n" +
            "\t\"strin_g\": \"test\",\n" +
            "\t\"testbool\": true,\n" +
            "\t\"testPriList\": [1, 2, 4],\n" +
            "\t\"testObjList\": [{\n" +
            "\t\t\"etst\": 11,\n" +
            "\t\t\"stst\": \"stst\"\n" +
            "\t}],\n" +
            "\t\"object\": {\n" +
            "\t\t\"subteststr\": \"dsds\",\n" +
            "\t\t\"subInt\": 1\n" +
            "\t}\n" +
            "}";
}
