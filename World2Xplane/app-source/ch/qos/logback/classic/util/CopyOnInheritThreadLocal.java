/*    */ package ch.qos.logback.classic.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class CopyOnInheritThreadLocal extends InheritableThreadLocal<HashMap<String, String>> {
/*    */   protected HashMap<String, String> childValue(HashMap<String, String> parentValue) {
/* 33 */     if (parentValue == null)
/* 34 */       return null; 
/* 36 */     return new HashMap<String, String>(parentValue);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classi\\util\CopyOnInheritThreadLocal.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */