package com.lyle.plugin.flutter.json.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.lyle.plugin.flutter.json.view.writablepannel.OnClickListener;
import com.lyle.plugin.flutter.json.view.writablepannel.WritablePannel;

public class FlutterToJsonAction extends AnAction implements OnClickListener {
    WritablePannel writablePannel;
    private VirtualFile virtualFile;

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
        writablePannel = WritablePannel.builder(this);
        virtualFile = psiFile.getVirtualFile();
        writablePannel.setVisible(true);
    }

    @Override
    public void onViewClick(String json) {
        if (json != null) {
            writablePannel.dispose();
        }
    }
}

