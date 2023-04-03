/*    */ package org.postgresql.util;
/*    */ 
/*    */ import java.text.MessageFormat;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class GT {
/* 26 */   private static final GT _gt = new GT();
/*    */   
/* 27 */   private static final Object[] noargs = new Object[0];
/*    */   
/*    */   private ResourceBundle _bundle;
/*    */   
/*    */   public static final String tr(String message) {
/* 30 */     return _gt.translate(message, null);
/*    */   }
/*    */   
/*    */   public static final String tr(String message, Object arg) {
/* 34 */     return _gt.translate(message, new Object[] { arg });
/*    */   }
/*    */   
/*    */   public static final String tr(String message, Object[] args) {
/* 38 */     return _gt.translate(message, args);
/*    */   }
/*    */   
/*    */   private GT() {
/*    */     try {
/* 47 */       this._bundle = ResourceBundle.getBundle("org.postgresql.translation.messages");
/* 49 */     } catch (MissingResourceException mre) {
/* 52 */       this._bundle = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   private final String translate(String message, Object[] args) {
/* 58 */     if (this._bundle != null && message != null)
/*    */       try {
/* 62 */         message = this._bundle.getString(message);
/* 64 */       } catch (MissingResourceException mre) {} 
/* 75 */     if (args == null)
/* 76 */       args = noargs; 
/* 81 */     if (message != null)
/* 83 */       message = MessageFormat.format(message, args); 
/* 86 */     return message;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\GT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */