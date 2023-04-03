/*     */ package com.world2xplane.GUI;
/*     */ 
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class JFilePicker extends JPanel {
/*     */   private String textFieldLabel;
/*     */   
/*     */   private String buttonLabel;
/*     */   
/*     */   private JLabel label;
/*     */   
/*     */   private JTextField textField;
/*     */   
/*     */   private JButton button;
/*     */   
/*     */   private JFileChooser fileChooser;
/*     */   
/*     */   private int mode;
/*     */   
/*     */   public static final int MODE_OPEN = 1;
/*     */   
/*     */   public static final int MODE_SAVE = 2;
/*     */   
/*     */   public JFilePicker(String textFieldLabel, String buttonLabel) {
/*  52 */     this.textFieldLabel = textFieldLabel;
/*  53 */     this.buttonLabel = buttonLabel;
/*  55 */     this.fileChooser = new JFileChooser();
/*  57 */     setLayout(new FlowLayout(1, 5, 5));
/*  60 */     this.label = new JLabel(textFieldLabel);
/*  62 */     this.textField = new JTextField(30);
/*  63 */     this.button = new JButton(buttonLabel);
/*  65 */     this.button.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  67 */             JFilePicker.this.buttonActionPerformed(evt);
/*     */           }
/*     */         });
/*  71 */     add(this.label);
/*  72 */     add(this.textField);
/*  73 */     add(this.button);
/*     */   }
/*     */   
/*     */   private void buttonActionPerformed(ActionEvent evt) {
/*  78 */     if (this.mode == 1) {
/*  79 */       this.fileChooser.setCurrentDirectory(new File("./"));
/*  80 */       if (this.fileChooser.showOpenDialog(this) == 0)
/*  81 */         this.textField.setText(this.fileChooser.getSelectedFile().getAbsolutePath()); 
/*  83 */     } else if (this.mode == 2 && 
/*  84 */       this.fileChooser.showSaveDialog(this) == 0) {
/*  85 */       this.textField.setText(this.fileChooser.getSelectedFile().getAbsolutePath());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addFileTypeFilter(String extension, String description) {
/*  91 */     FileTypeFilter filter = new FileTypeFilter(extension, description);
/*  92 */     this.fileChooser.addChoosableFileFilter(filter);
/*     */   }
/*     */   
/*     */   public void setMode(int mode) {
/*  96 */     this.mode = mode;
/*     */   }
/*     */   
/*     */   public String getSelectedFilePath() {
/* 100 */     return this.textField.getText();
/*     */   }
/*     */   
/*     */   public void setSelectedFilePath(String path) {
/* 104 */     this.textField.setText((new File(path)).getAbsolutePath());
/*     */   }
/*     */   
/*     */   public JFileChooser getFileChooser() {
/* 108 */     return this.fileChooser;
/*     */   }
/*     */   
/*     */   public String getTextFieldLabel() {
/* 112 */     return this.textFieldLabel;
/*     */   }
/*     */   
/*     */   public void setTextFieldLabel(String textFieldLabel) {
/* 116 */     this.textFieldLabel = textFieldLabel;
/*     */   }
/*     */   
/*     */   public String getButtonLabel() {
/* 120 */     return this.buttonLabel;
/*     */   }
/*     */   
/*     */   public void setButtonLabel(String buttonLabel) {
/* 124 */     this.buttonLabel = buttonLabel;
/*     */   }
/*     */   
/*     */   public JLabel getLabel() {
/* 128 */     return this.label;
/*     */   }
/*     */   
/*     */   public void setLabel(JLabel label) {
/* 132 */     this.label = label;
/*     */   }
/*     */   
/*     */   public JTextField getTextField() {
/* 136 */     return this.textField;
/*     */   }
/*     */   
/*     */   public void setTextField(JTextField textField) {
/* 140 */     this.textField = textField;
/*     */   }
/*     */   
/*     */   public JButton getButton() {
/* 144 */     return this.button;
/*     */   }
/*     */   
/*     */   public void setButton(JButton button) {
/* 148 */     this.button = button;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\JFilePicker.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */