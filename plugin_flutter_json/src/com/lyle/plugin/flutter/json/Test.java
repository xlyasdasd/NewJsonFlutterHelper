package com.lyle.plugin.flutter.json;

public class Test {
    public static void main(String[] arg){
        new ClassGenerator().generate("User", "{\n" +
                "\t\"sort\": 1,\n" +
                "\t\"numbers\": [1, 2, 3, 4],\n" +
                "\t\"person\": [{\n" +
                "\t\t\"name\": \"fff\",\n" +
                "\t\t\"age\": 12,\n" +
                "\t\t\"timestamp\": 123124324234234234\n" +
                "\t}],\n" +
                "\t\"state\": {\n" +
                "\t\t\"name\": \"fff\",\n" +
                "\t\t\"type\": 22.22,\n" +
                "\t\t\"size\": \"fdf,3,4,34\"\n" +
                "\t}\n" +
                "}");
    }
}
