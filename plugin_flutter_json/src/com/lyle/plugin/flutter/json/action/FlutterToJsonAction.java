package com.lyle.plugin.flutter.json.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import com.lyle.plugin.flutter.json.utils.ClassProcessor;
import com.lyle.plugin.flutter.json.utils.JsonTransformerUtils;
import com.lyle.plugin.flutter.json.view.writablepannel.OnClickListener;
import com.lyle.plugin.flutter.json.view.writablepannel.WritablePannel;

import java.io.IOException;

import static com.lyle.plugin.flutter.json.utils.StringUtils.toUpperCaseFirstOne;
import static com.lyle.plugin.flutter.json.utils.StringUtils.toUpperCaseParams;

public class FlutterToJsonAction extends AnAction implements OnClickListener {
    private WritablePannel writablePannel;
    private VirtualFile virtualFile;
    private Project project;

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        if (anActionEvent == null) {
            return;
        }
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        this.project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        if (editor == null || project == null) {
            return;
        }
        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (psiFile == null) {
            return;
        }
        writablePannel = WritablePannel.builder(this);
        virtualFile = psiFile.getVirtualFile();
        writablePannel.setVisible(true);
    }

    @Override
    public void onViewClick(String json) {
        if (json != null) {

            writablePannel.dispose();
            try {
                virtualFile.setBinaryContent(ClassProcessor.buildClass(JsonTransformerUtils.transformerJson(json, toUpperCaseFirstOne(toUpperCaseParams(virtualFile.getNameWithoutExtension())))).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showInfoDialog("json error!");
        }
    }

    private void showInfoDialog(String message) {
        Messages.showErrorDialog(message, "Info");
    }

    private void writeClass(String classStr) {
        WriteCommandAction.runWriteCommandAction(project, () -> {
            try {
                virtualFile.setBinaryContent(classStr.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}


