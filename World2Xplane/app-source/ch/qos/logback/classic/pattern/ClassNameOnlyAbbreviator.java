/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ public class ClassNameOnlyAbbreviator implements Abbreviator {
/*    */   public String abbreviate(String fqClassName) {
/* 30 */     int lastIndex = fqClassName.lastIndexOf('.');
/* 31 */     if (lastIndex != -1)
/* 32 */       return fqClassName.substring(lastIndex + 1, fqClassName.length()); 
/* 34 */     return fqClassName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\ClassNameOnlyAbbreviator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */