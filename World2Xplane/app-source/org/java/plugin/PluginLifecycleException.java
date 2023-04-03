/*    */ package org.java.plugin;
/*    */ 
/*    */ public class PluginLifecycleException extends JpfException {
/*    */   private static final long serialVersionUID = -4019294858687542301L;
/*    */   
/*    */   public PluginLifecycleException(String packageName, String messageKey) {
/* 34 */     super(packageName, messageKey, null, null);
/*    */   }
/*    */   
/*    */   public PluginLifecycleException(String packageName, String messageKey, Object data) {
/* 44 */     super(packageName, messageKey, data, null);
/*    */   }
/*    */   
/*    */   public PluginLifecycleException(String packageName, String messageKey, Object data, Throwable cause) {
/* 55 */     super(packageName, messageKey, data, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\PluginLifecycleException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */