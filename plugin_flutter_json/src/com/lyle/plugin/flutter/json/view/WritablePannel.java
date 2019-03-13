package com.lyle.plugin.flutter.json.view;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;

public class WritablePannel extends JFrame {

    @Override
    public String getTitle() {
        return "format json to dart model";
    }

    public static WritablePannel builder(OnClickListener onClickListener) {
        WritablePannel jFrame = new WritablePannel();
        jFrame.setSize(700, 400);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.add(initView(onClickListener));
        return jFrame;
    }

    private static JPanel initView(OnClickListener onClickListener) {
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("input json and click button");
        jLabel.setBounds(10, 0, 500, 40);
        JTextArea editTextView = new JTextArea();
        editTextView.setBounds(0, 50, 700, 400);
        JBScrollPane jbScrollPane = new JBScrollPane(editTextView);
        jbScrollPane.setVerticalScrollBarPolicy(JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jbScrollPane.setHorizontalScrollBarPolicy(JBScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jbScrollPane.setBounds(0, 50, 700, 400);
        JButton jButton = new JButton("ok");
        jButton.setBounds(600, 10, 80, 30);
        jButton.addActionListener(e -> onClickListener.onViewClick(editTextView.getText()));
        return jPanel;

    }

}
