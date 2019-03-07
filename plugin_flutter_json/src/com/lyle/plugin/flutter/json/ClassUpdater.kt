package com.lyle.plugin.flutter.json

import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import org.apache.commons.httpclient.NameValuePair
import java.lang.IllegalStateException
import java.util.HashMap

class ClassUpdater {
    val classes = mutableMapOf<String, List<Param>>()

    fun generate(name: String, jsonText: String): String {
        return try {
            val fields = Param.json2Params(JsonParser().parse(jsonText).asJsonObject)
            "class $name {\n${printClassWithParams(fields, 2, name)}\n}\n${buildClasses()}"
        } catch (jsonParseException: JsonParseException) {
            jsonParseException.printStackTrace()
            "error: not supported json"
        } catch (illegalStateException: IllegalStateException) {
            illegalStateException.printStackTrace()

            if (illegalStateException.message?.startsWith("Not a JSON Object") == true) {
                "error: not supported json"
            } else {
                "error: unknown"
            }
        }
    }

    private fun printClassWithParams(params: List<Param>, space: Int, className: String): String {
        val sb = StringBuilder()

        val tempClasses = HashMap<String, List<Param>>()

        var spaceStr = ""
        repeat(space) { spaceStr += " " }

        val orderedList = params
                .filter { it.key == "String" || it.key == "int" || it.key == "double" || it.key == "bool" || it.key == "num" }
                .sortedBy { it.key }
                .map {
                    sb.append("$spaceStr${it.key} ${it.value};\n")
                    it.value
                }

        val objectList = params
                .filter { it.key == "object" }
                .sortedBy { it.value }
                .map {
                    val clazzName = Utils.toUpperCaseFirstOne(it.value + "Bean")
                    classes[clazzName] = it.clazz
                    tempClasses[clazzName] = it.clazz
                    sb.append(spaceStr).append(clazzName).append(" ").append(it.value).append(";").append("\n")
                    NameValuePair(clazzName, it.value)
                }

        val listBaseList = params
                .filter { it.key.startsWith("List<") }
                .sortedBy { it.value }
                .map {
                    sb.append(spaceStr).append(it.key).append(" ").append(it.value).append(";").append("\n")
                    NameValuePair(it.key, it.value)
                }

        val listList = params
                .filter { "list" == it.key }
                .sortedBy { it.value }
                .map {
                    val clazzName = Utils.toUpperCaseFirstOne(it.value + "ListBean")
                    classes[clazzName] = it.clazz
                    tempClasses[clazzName] = it.clazz
                    sb.append(spaceStr).append("List<").append(clazzName).append(">").append(" ").append(it.value).append(";").append("\n")
                    NameValuePair(clazzName, it.value)
                }

        val tempSpaceStr = "$spaceStr  "


        /**
         * 构造
         */
        sb.append("\n").append(spaceStr)
                .append(className).append("({")

        orderedList.forEach {
            sb.append("this").append(".").append(it)
            sb.append(", ")
        }

        objectList.forEach {
            sb.append("this").append(".").append(it.value)
            sb.append(", ")
        }

        listList.forEach {
            sb.append("this").append(".").append(it.value)
            sb.append(", ")
        }

        listBaseList.forEach {
            sb.append("this").append(".").append(it.value)
            sb.append(", ")
        }
        if (sb.endsWith(", ")) {
            sb.delete(sb.lastIndexOf(", "), sb.length)
        }
        sb.append("});\n")


        sb.append("\n").append(spaceStr)
                .append(className).append(".fromJson").append("(Map<String, dynamic> json) {")
                .append(tempSpaceStr)

        orderedList.forEach {
            sb.append("\n").append(tempSpaceStr).append("this").append(".").append(it).append(" = ").append("json['").append(it).append("'];")
        }

        objectList.forEach {
            sb.append("\n").append(tempSpaceStr).append("this").append(".").append(it.value).append(" = ").append(it.name).append(".fromJson(json['").append(it.value).append("']);")
        }

        listList.forEach {
            sb.append("\n").append(tempSpaceStr).append("this").append(".").append(it.value).append(" = ").append("(json['").append(it.value).append("'] as List)!=null?(json['").append(it.value).append("'] as List).map((i) => ").append(it.name).append(".fromJson(i)).toList():null;")
        }

        listBaseList.forEach {
            sb.append("\n")
            sb.append("\n").append(tempSpaceStr).append("List<dynamic> ").append(it.value).append("List").append(" = json['").append(it.value).append("'];")
            sb.append("\n").append(tempSpaceStr).append("this").append(".").append(it.value).append(" = new List();")

            var function = "o.toString()"
            when (it.name) {
                "List<int>" -> function = "int.parse(o.toString())"
                "List<double>" -> function = "double.parse(o.toString())"
                "List<bool>" -> function = "o.toString() == 'true'"
            }

            sb.append("\n").append(tempSpaceStr).append("this").append(".").append(it.value).append(".addAll(").append(it.value).append("List").append(".map((o) => ").append(function).append("));")
        }

        sb.append("\n").append(spaceStr).append("}\n\n")

        sb.append(spaceStr)
                .append("Map<String, dynamic> toJson() {\n").append(tempSpaceStr).append("final Map<String, dynamic> data = new Map<String, dynamic>();")
        orderedList.forEach {
            sb.append("\n").append(tempSpaceStr).append("data['").append(it).append("'] = ").append("this").append(".").append(it).append(";")
        }

        objectList.forEach {
            sb.append("\n").append(tempSpaceStr).append("data['").append(it.value).append("'] = ").append("this").append(".").append(it.value).append(".toJson();")
        }

        listList.forEach {
            sb.append("\n").append(tempSpaceStr).append("data['").append(it.value).append("'] = ").append("this").append(".").append(it.value).append(" != null?this.").append(it.value).append(".map((i) => i.toJson()).toList():null;")
        }

        listBaseList.forEach {
            sb.append("\n").append(tempSpaceStr).append("data['").append(it.value).append("'] = ").append("this").append(".").append(it.value).append(";")
        }

        sb.append("\n").append(tempSpaceStr).append("return data;\n")
        sb.append(spaceStr).append("}\n")


        tempClasses.forEach { key, value ->
            printClassWithParams(value, space + 2, key)
        }

        return sb.toString()
    }

    private fun buildClasses(): String {
        val sb = StringBuilder()

        classes.forEach { key, value ->
            sb.append("\n")
                    .append("class").append(" ").append(key).append(" ").append("{").append("\n")
                    .append(printClassWithParams(value, 2, key))
                    .append("}").append("\n")
        }

        return sb.toString()
    }
}