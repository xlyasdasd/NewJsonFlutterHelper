package com.lyle.plugin.flutter.json;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

public class ParamGenerator {

    StringBuilder stringBuilder = new StringBuilder();

    public static String from(PsiClass targetClass) {
        PsiField[] fields = targetClass.getAllFields();
        for (int i = 0; i < fields.length; i++) {
            PsiField psiField = fields[i];
            psiField.getType().getCanonicalText();
        }
        return null;
    }
}
