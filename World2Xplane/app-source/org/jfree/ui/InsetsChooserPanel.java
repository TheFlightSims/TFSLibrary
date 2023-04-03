/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.TitledBorder;
/*     */ 
/*     */ public class InsetsChooserPanel extends JPanel {
/*     */   private JTextField topValueEditor;
/*     */   
/*     */   private JTextField leftValueEditor;
/*     */   
/*     */   private JTextField bottomValueEditor;
/*     */   
/*     */   private JTextField rightValueEditor;
/*     */   
/*  83 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.ui.LocalizationBundle");
/*     */   
/*     */   public InsetsChooserPanel() {
/*  91 */     this(new Insets(0, 0, 0, 0));
/*     */   }
/*     */   
/*     */   public InsetsChooserPanel(Insets current) {
/* 101 */     current = (current == null) ? new Insets(0, 0, 0, 0) : current;
/* 103 */     this.topValueEditor = new JTextField(new IntegerDocument(), "" + current.top, 0);
/* 105 */     this.leftValueEditor = new JTextField(new IntegerDocument(), "" + current.left, 0);
/* 107 */     this.bottomValueEditor = new JTextField(new IntegerDocument(), "" + current.bottom, 0);
/* 109 */     this.rightValueEditor = new JTextField(new IntegerDocument(), "" + current.right, 0);
/* 112 */     JPanel panel = new JPanel(new GridBagLayout());
/* 113 */     panel.setBorder(new TitledBorder(localizationResources.getString("Insets")));
/* 117 */     panel.add(new JLabel(localizationResources.getString("Top")), new GridBagConstraints(1, 0, 3, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
/* 123 */     panel.add(new JLabel(" "), new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 10, 1, new Insets(0, 12, 0, 12), 8, 0));
/* 126 */     panel.add(this.topValueEditor, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
/* 129 */     panel.add(new JLabel(" "), new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, 10, 1, new Insets(0, 12, 0, 11), 8, 0));
/* 134 */     panel.add(new JLabel(localizationResources.getString("Left")), new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 10, 1, new Insets(0, 4, 0, 4), 0, 0));
/* 138 */     panel.add(this.leftValueEditor, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
/* 141 */     panel.add(new JLabel(" "), new GridBagConstraints(2, 2, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 12, 0, 12), 8, 0));
/* 144 */     panel.add(this.rightValueEditor, new GridBagConstraints(3, 2, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
/* 147 */     panel.add(new JLabel(localizationResources.getString("Right")), new GridBagConstraints(4, 2, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 4, 0, 4), 0, 0));
/* 153 */     panel.add(this.bottomValueEditor, new GridBagConstraints(2, 3, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
/* 158 */     panel.add(new JLabel(localizationResources.getString("Bottom")), new GridBagConstraints(1, 4, 3, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
/* 162 */     setLayout(new BorderLayout());
/* 163 */     add(panel, "Center");
/*     */   }
/*     */   
/*     */   public Insets getInsetsValue() {
/* 174 */     return new Insets(Math.abs(stringToInt(this.topValueEditor.getText())), Math.abs(stringToInt(this.leftValueEditor.getText())), Math.abs(stringToInt(this.bottomValueEditor.getText())), Math.abs(stringToInt(this.rightValueEditor.getText())));
/*     */   }
/*     */   
/*     */   protected int stringToInt(String value) {
/* 191 */     value = value.trim();
/* 192 */     if (value.length() == 0)
/* 193 */       return 0; 
/*     */     try {
/* 197 */       return Integer.parseInt(value);
/* 199 */     } catch (NumberFormatException e) {
/* 200 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeNotify() {
/* 209 */     super.removeNotify();
/* 210 */     removeAll();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\InsetsChooserPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */