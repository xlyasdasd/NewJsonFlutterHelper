package com.lyle.plugin.flutter.json;

import com.intellij.openapi.vfs.VirtualFile;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;

public class Utils {
    /**
     * 将字符串复制到剪切板。
     */
    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }

    // 首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (s.contains("_")) {
            String[] a = s.split("_");
            StringBuilder builder = new StringBuilder();
            for (String anA : a) {
                if (Character.isUpperCase(anA.charAt(0)))
                    builder.append(anA);
                else
                    builder.append(Character.toUpperCase(anA.charAt(0))).append(anA.substring(1));
            }
            return builder.toString();
        }
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    // 下划线参数转驼峰
    public static String toUpperCaseParams(String s) {
        if (s.contains("_")) {
            String[] a = s.split("_");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < a.length; i++) {
                if (i==0){
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

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    // 将 string 写入文件
    public static void writeToFile(VirtualFile file, String content) {
        try {
            file.setBinaryContent(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
