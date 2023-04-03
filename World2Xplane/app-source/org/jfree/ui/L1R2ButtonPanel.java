/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.GridLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class L1R2ButtonPanel extends JPanel {
/*     */   private JButton left;
/*     */   
/*     */   private JButton right1;
/*     */   
/*     */   private JButton right2;
/*     */   
/*     */   public L1R2ButtonPanel(String label1, String label2, String label3) {
/*  79 */     setLayout(new BorderLayout());
/*  82 */     this.left = new JButton(label1);
/*  84 */     JPanel rightButtonPanel = new JPanel(new GridLayout(1, 2));
/*  85 */     this.right1 = new JButton(label2);
/*  86 */     this.right2 = new JButton(label3);
/*  87 */     rightButtonPanel.add(this.right1);
/*  88 */     rightButtonPanel.add(this.right2);
/*  91 */     add(this.left, "West");
/*  92 */     add(rightButtonPanel, "East");
/*     */   }
/*     */   
/*     */   public JButton getLeftButton() {
/* 102 */     return this.left;
/*     */   }
/*     */   
/*     */   public JButton getRightButton1() {
/* 111 */     return this.right1;
/*     */   }
/*     */   
/*     */   public JButton getRightButton2() {
/* 120 */     return this.right2;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\L1R2ButtonPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */