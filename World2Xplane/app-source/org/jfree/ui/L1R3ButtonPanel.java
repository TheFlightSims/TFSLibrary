/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class L1R3ButtonPanel extends JPanel {
/*     */   private JButton left;
/*     */   
/*     */   private JButton right1;
/*     */   
/*     */   private JButton right2;
/*     */   
/*     */   private JButton right3;
/*     */   
/*     */   public L1R3ButtonPanel(String label1, String label2, String label3, String label4) {
/*  82 */     setLayout(new BorderLayout());
/*  85 */     JPanel panel = new JPanel(new BorderLayout());
/*  86 */     JPanel panel2 = new JPanel(new BorderLayout());
/*  87 */     this.left = new JButton(label1);
/*  88 */     this.right1 = new JButton(label2);
/*  89 */     this.right2 = new JButton(label3);
/*  90 */     this.right3 = new JButton(label4);
/*  93 */     panel.add(this.left, "West");
/*  94 */     panel2.add(this.right1, "East");
/*  95 */     panel.add(panel2, "Center");
/*  96 */     panel.add(this.right2, "East");
/*  97 */     add(panel, "Center");
/*  98 */     add(this.right3, "East");
/*     */   }
/*     */   
/*     */   public JButton getLeftButton() {
/* 108 */     return this.left;
/*     */   }
/*     */   
/*     */   public JButton getRightButton1() {
/* 117 */     return this.right1;
/*     */   }
/*     */   
/*     */   public JButton getRightButton2() {
/* 126 */     return this.right2;
/*     */   }
/*     */   
/*     */   public JButton getRightButton3() {
/* 135 */     return this.right3;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\L1R3ButtonPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */