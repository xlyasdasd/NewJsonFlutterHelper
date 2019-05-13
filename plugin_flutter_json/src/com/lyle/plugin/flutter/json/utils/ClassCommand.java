package com.lyle.plugin.flutter.json.utils;

public class ClassCommand {
    public static final String CLASS_TITLE = "class %1$s {\n";
    public static final String PARAMS = "  %1$s %2$s;\n";
    public static final String CONSTRUCT_FRONT = "  %1$s({";
    public static final String CONSTRUCT_PARAMS = "this.%1$s, ";
    public static final String CONSTRUCT_END = "});";
    public static final String ENTER = "\n";
    public static final String FROM_JSON_FRONT = "%1$s.fromJson(Map<String, dynamic> json) {\n";
    public static final String FROM_JSON_INPUT = "    this.%1$s = json['%2$s'];\n";
    public static final String SIMPLY_TAB_FUN_END = "  }\n";
    public static final String TO_JSON_FRONT = "  Map<String, dynamic> toJson() {\n    final Map<String, dynamic> data = new Map<String, dynamic>();\n";
    public static final String TO_JSON_INPUT = "    data['%1$s'] = this.%2$s;\n";
    public static final String TO_JSON_OBJECT_INPUT = "    data['%1$s'] = this.%2$s;\n";
    public static final String TO_JSON_LIST_INPUT = "    data['%1$s'] = this.%2$s;\n";

}
