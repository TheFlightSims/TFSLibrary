/*    */ package org.java.plugin;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import org.java.plugin.util.ResourceManager;
/*    */ 
/*    */ public abstract class JpfException extends Exception {
/*    */   private final String packageName;
/*    */   
/*    */   private final String messageKey;
/*    */   
/*    */   private final Object data;
/*    */   
/*    */   protected JpfException(String aPackageName, String aMessageKey, Object aData, Throwable cause) {
/* 37 */     super(ResourceManager.getMessage(aPackageName, aMessageKey, aData), cause);
/* 39 */     this.packageName = aPackageName;
/* 40 */     this.messageKey = aMessageKey;
/* 41 */     this.data = aData;
/*    */   }
/*    */   
/*    */   public String getMessage(Locale locale) {
/* 49 */     return ResourceManager.getMessage(this.packageName, this.messageKey, locale, this.data);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\JpfException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */