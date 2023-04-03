/*    */ package ch.qos.logback.classic.util;
/*    */ 
/*    */ import ch.qos.logback.core.util.Loader;
/*    */ 
/*    */ public class EnvUtil {
/*    */   public static boolean isGroovyAvailable() {
/* 25 */     ClassLoader classLoader = Loader.getClassLoaderOfClass(EnvUtil.class);
/*    */     try {
/* 27 */       Class<?> bindingClass = classLoader.loadClass("groovy.lang.Binding");
/* 28 */       return (bindingClass != null);
/* 29 */     } catch (ClassNotFoundException e) {
/* 30 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classi\\util\EnvUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */