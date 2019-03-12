package com.lyle.plugin.flutter.json;

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
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import jdk.nashorn.internal.parser.JSONParser;

import javax.swing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.xmlbeans.impl.jam.xml.JamXmlElements.ANNOTATION;

public class GenerateCodeAction extends AnAction {

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
        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("d",);
//        String classesString = new ClassGenerator().generate(Utils.toUpperCaseFirstOne(virtualFile.getNameWithoutExtension()), )
//        Utils.writeToFile(virtualFile, classesString)
//        ClassGenerator.
//        CaretModel caretModel = editor.getCaretModel();
//
//        caretModel.getVisualLineEnd();


//        showInfoDialog(String.valueOf(document.isLineModified(caretModel.getVisualPosition().line)));
//        WriteCommandAction.runWriteCommandAction(project, () -> {
//            document.insertString(caretModel.getOffset(), ANNOTATION);
//            document.insertString(0, LIBRARY_IMPORT + JSON_PACKAGE_IMPORT
//                    + PART_IMPORT);
//        });



//        JFrame jFrame = new JFrame("format json to dart model");
//        frameView.setFrame(jFrame);
//        jFrame.setSize(700, 470);
//        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        jFrame.setVisible(true);
//        jFrame.add(frameView.build());
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
}

