/*    */ package org.java.plugin.registry;
/*    */ 
/*    */ import org.java.plugin.JpfException;
/*    */ 
/*    */ public class ManifestProcessingException extends JpfException {
/*    */   private static final long serialVersionUID = -5670886724425293056L;
/*    */   
/*    */   public ManifestProcessingException(String packageName, String messageKey) {
/* 36 */     super(packageName, messageKey, null, null);
/*    */   }
/*    */   
/*    */   public ManifestProcessingException(String packageName, String messageKey, Object data) {
/* 46 */     super(packageName, messageKey, data, null);
/*    */   }
/*    */   
/*    */   public ManifestProcessingException(String packageName, String messageKey, Object data, Throwable cause) {
/* 57 */     super(packageName, messageKey, data, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\ManifestProcessingException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */