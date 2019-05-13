package com.lyle.plugin.flutter.json.utils;

public class ClassCommand {
    public static final String CLASS_TITLE = "class %1$s {\n";
    public static final String PARAMS = "  %1$s %2$s;\n";
    public static final String CONSTRUCT_FRONT = "  %1$s({";
    public static final String CONSTRUCT_PARAMS = "this.%1$s, ";
    public static final String CONSTRUCT_END = "});\n";
    public static final String ENTER = "\n";
    public static final String FROM_JSON_FRONT = "  %1$s.fromJson(Map<String, dynamic> json) {\n";
    public static final String FROM_JSON_INPUT = "    this.%1$s = json['%2$s'];\n";
    public static final String FROM_JSON_LIST_OBJECT_JSON_INPUT = "    this.%1$s = (json['%2$s'] as List)!=null?(json['%3$s'] as List).map((i) => %4$s.fromJson(i)).toList():null;\n";
    public static final String FROM_JSON_LIST_PRI_JSON_INPUT = "    List<dynamic> %1$sList = json['%2$s'];\n    this.%1$s = new List();\n    this.%1$s.addAll(%1$sList.map((o) => %3$s.parse(o.toString())));\n";
    public static final String FROM_JSON_OBJ_INPUT = "    this.%1$s = json['%2$s'] != null ? %3$s.fromJson(json['%2$s']) : null;\n";
    public static final String SIMPLY_TAB_FUN_END = "  }\n";
    public static final String SIMPLY_FUN_END = "}\n";
    public static final String TO_JSON_FRONT = "  Map<String, dynamic> toJson() {\n    final Map<String, dynamic> data = new Map<String, dynamic>();\n";
    public static final String TO_JSON_INPUT = "    data['%1$s'] = this.%2$s;\n";
    public static final String TO_JSON_OBJECT_INPUT = "    if (this.%1$s  !=null) {\n" +
            "      data['%2$s'] = this.%1$s.toJson();\n" +
            "    }\n";
    public static final String TO_JSON_LIST_OBJECT_INPUT = "    data['%1$s'] = this.%2$s != null?this.%2$s.map((i) => i.toJson()).toList():null;\n";
    public static final String TO_JSON_LIST_PRI_JSON_INPUT = "    data['%1$s'] = this.%2$s;\n";

    public static final String TO_JSON_END = "    return data;\n" +
            "  }\n";
}
