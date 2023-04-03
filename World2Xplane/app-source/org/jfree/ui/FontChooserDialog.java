/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Font;
/*     */ import java.awt.Frame;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class FontChooserDialog extends StandardDialog {
/*     */   private FontChooserPanel fontChooserPanel;
/*     */   
/*     */   public FontChooserDialog(Dialog owner, String title, boolean modal, Font font) {
/*  73 */     super(owner, title, modal);
/*  74 */     setContentPane(createContent(font));
/*     */   }
/*     */   
/*     */   public FontChooserDialog(Frame owner, String title, boolean modal, Font font) {
/*  86 */     super(owner, title, modal);
/*  87 */     setContentPane(createContent(font));
/*     */   }
/*     */   
/*     */   public Font getSelectedFont() {
/*  96 */     return this.fontChooserPanel.getSelectedFont();
/*     */   }
/*     */   
/*     */   private JPanel createContent(Font font) {
/* 107 */     JPanel content = new JPanel(new BorderLayout());
/* 108 */     content.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/* 109 */     if (font == null)
/* 110 */       font = new Font("Dialog", 10, 0); 
/* 112 */     this.fontChooserPanel = new FontChooserPanel(font);
/* 113 */     content.add(this.fontChooserPanel);
/* 115 */     JPanel buttons = createButtonPanel();
/* 116 */     buttons.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
/* 117 */     content.add(buttons, "South");
/* 119 */     return content;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\FontChooserDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */