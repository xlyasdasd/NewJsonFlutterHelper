package com.lyle.plugin.flutter.json.utils;

public class StringUtils {
    // 下划线参数转驼峰
    public static String toUpperCaseParams(String s) {
        if (s.contains("_")) {
            String[] a = s.split("_");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < a.length; i++) {
                if (i == 0) {
                    builder.append(a[i]);
                }
                if (Character.isUpperCase(a[i].charAt(0)))
                    builder.append(a[i]);
                else if (i != 0)
                    builder.append(Character.toUpperCase(a[i].charAt(0))).append(a[i].substring(1));
            }

            return builder.toString();
        }
        return s;
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}
