/*    */ package org.jfree.ui;
/*    */ 
/*    */ import java.awt.Insets;
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class InsetsTextField extends JTextField {
/* 59 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.ui.LocalizationBundle");
/*    */   
/*    */   public InsetsTextField(Insets insets) {
/* 70 */     setInsets(insets);
/* 71 */     setEnabled(false);
/*    */   }
/*    */   
/*    */   public String formatInsetsString(Insets insets) {
/* 82 */     insets = (insets == null) ? new Insets(0, 0, 0, 0) : insets;
/* 83 */     return localizationResources.getString("T") + insets.top + ", " + localizationResources.getString("L") + insets.left + ", " + localizationResources.getString("B") + insets.bottom + ", " + localizationResources.getString("R") + insets.right;
/*    */   }
/*    */   
/*    */   public void setInsets(Insets insets) {
/* 98 */     setText(formatInsetsString(insets));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\InsetsTextField.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */