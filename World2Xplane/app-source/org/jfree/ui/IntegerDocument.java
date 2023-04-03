/*    */ package org.jfree.ui;
/*    */ 
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.PlainDocument;
/*    */ 
/*    */ public class IntegerDocument extends PlainDocument {
/*    */   public void insertString(int i, String s, AttributeSet attributes) throws BadLocationException {
/* 69 */     super.insertString(i, s, attributes);
/* 70 */     if (s != null && (!s.equals("-") || i != 0 || s.length() >= 2))
/*    */       try {
/* 72 */         Integer.parseInt(getText(0, getLength()));
/* 74 */       } catch (NumberFormatException e) {
/* 75 */         remove(i, s.length());
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\IntegerDocument.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */