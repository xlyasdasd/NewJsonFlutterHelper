package com.lyle.plugin.flutter.json.action;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.ide.util.TreeClassChooserFactoryImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.lyle.plugin.flutter.json.FrameView;
import com.lyle.plugin.flutter.json.view.OnClickListener;
import jdk.nashorn.internal.parser.JSONParser;

import javax.swing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.xmlbeans.impl.jam.xml.JamXmlElements.ANNOTATION;

public class GenerateCodeAction extends AnAction implements OnClickListener {

    private CaretModel mCaret;

    private int mClassLine = -1;

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        if (anActionEvent == null) {
            return;
        }
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        if (editor == null || project == null) {
            return;
        }
        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (psiFile == null) {
            return;
        }
        TreeClassChooserFactory classChooserFactory = new TreeClassChooserFactoryImpl(project);
        VirtualFile virtualFile = psiFile.getVirtualFile();
        FrameView frameView = new FrameView(virtualFile);

        Document document = editor.getDocument();


        parseClassName(document.getText());

        CaretModel caretModel = editor.getCaretModel();
        try {
            int startPosition = caretModel.getOffset();
            int classStartPosition = -1;
            while (true) {
                PsiElement element = psiFile.getViewProvider().findElementAt(startPosition);
                if ("{".equals(element.getText().trim())) {
                    classStartPosition = startPosition;
                    break;
                }
                startPosition--;
            }

            if (classStartPosition == -1) {//没有找到开始的{
                return;
            }
            while (true) {
                PsiElement element = psiFile.getViewProvider().findElementAt(classStartPosition);
                if ("{".equals(element.getText().trim())) {
                    classStartPosition = startPosition;
                    break;
                }
                startPosition--;
            }
        } catch (Exception e) {

        }

//        showInfoDialog(psiFile.getViewProvider().findElementAt(caretModel.getOffset()).isEquivalentTo(
//
//        );

//        PsiType psiType  =  getTargetClass(editor,psiFile).getAllFields()[0].getType();
////        getTargetClass(editor,psiFile).getAllFields()[0].getType().get()
//        String paramsString = ParamGenerator.from(getTargetClass(editor,psiFile));


        WriteCommandAction.runWriteCommandAction(project, () -> {
            document.insertString(caretModel.getOffset(), "sdsdsdsd");

        });

//        StringBuilder sdsdsdsd = new StringBuilder();
//        sdsdsdsd
//        JFrame jFrame = new JFrame("format json to dart model");
//        frameView.setFrame(jFrame);
//        jFrame.setSize(700, 470);
//        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        jFrame.setVisible(true);
//        jFrame.add(frameView.build());
    }


    private PsiClass getTargetClass(Editor editor, PsiFile file) {
        int offset = editor.getCaretModel().getOffset();
        PsiElement element = file.findElementAt(offset);
        if (element == null) {
            return null;
        } else {
            PsiClass target = PsiTreeUtil.getParentOfType(element, PsiClass.class);
            return target instanceof SyntheticElement ? null : target;
        }
    }


    private String parseClassName(String fileContent) {
        if (fileContent == null || fileContent.equals("")) {
            return "";
        }

        String rgex = "class(.*?)(extends(.*?))?(implements(.*?))?\\{";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(fileContent);
        while (m.find()) {
            return m.group(1).trim();
        }
        return "";
    }

    private void showInfoDialog(String message) {
        Messages.showErrorDialog(message, "Info");
    }

    @Override
    public void onViewClick(String json) {
        if (json!=null){

        }
    }
}

