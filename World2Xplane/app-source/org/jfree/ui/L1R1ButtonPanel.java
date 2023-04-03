/*    */ package org.jfree.ui;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class L1R1ButtonPanel extends JPanel {
/*    */   private JButton left;
/*    */   
/*    */   private JButton right;
/*    */   
/*    */   public L1R1ButtonPanel(String leftLabel, String rightLabel) {
/* 74 */     setLayout(new BorderLayout());
/* 75 */     this.left = new JButton(leftLabel);
/* 76 */     this.right = new JButton(rightLabel);
/* 77 */     add(this.left, "West");
/* 78 */     add(this.right, "East");
/*    */   }
/*    */   
/*    */   public JButton getLeftButton() {
/* 88 */     return this.left;
/*    */   }
/*    */   
/*    */   public JButton getRightButton() {
/* 97 */     return this.right;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\L1R1ButtonPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */