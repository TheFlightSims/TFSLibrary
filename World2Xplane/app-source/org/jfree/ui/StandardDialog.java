/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class StandardDialog extends JDialog implements ActionListener {
/*     */   private boolean cancelled;
/*     */   
/*  68 */   protected static final ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.ui.LocalizationBundle");
/*     */   
/*     */   public StandardDialog(Frame owner, String title, boolean modal) {
/*  79 */     super(owner, title, modal);
/*  80 */     this.cancelled = false;
/*     */   }
/*     */   
/*     */   public StandardDialog(Dialog owner, String title, boolean modal) {
/*  91 */     super(owner, title, modal);
/*  92 */     this.cancelled = false;
/*     */   }
/*     */   
/*     */   public boolean isCancelled() {
/* 101 */     return this.cancelled;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 110 */     String command = event.getActionCommand();
/* 111 */     if (!command.equals("helpButton"))
/* 114 */       if (command.equals("okButton")) {
/* 115 */         this.cancelled = false;
/* 116 */         setVisible(false);
/* 118 */       } else if (command.equals("cancelButton")) {
/* 119 */         this.cancelled = true;
/* 120 */         setVisible(false);
/*     */       }  
/*     */   }
/*     */   
/*     */   protected JPanel createButtonPanel() {
/* 132 */     L1R2ButtonPanel buttons = new L1R2ButtonPanel(localizationResources.getString("Help"), localizationResources.getString("OK"), localizationResources.getString("Cancel"));
/* 136 */     JButton helpButton = buttons.getLeftButton();
/* 137 */     helpButton.setActionCommand("helpButton");
/* 138 */     helpButton.addActionListener(this);
/* 140 */     JButton okButton = buttons.getRightButton1();
/* 141 */     okButton.setActionCommand("okButton");
/* 142 */     okButton.addActionListener(this);
/* 144 */     JButton cancelButton = buttons.getRightButton2();
/* 145 */     cancelButton.setActionCommand("cancelButton");
/* 146 */     cancelButton.addActionListener(this);
/* 148 */     buttons.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
/* 149 */     return buttons;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\StandardDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */