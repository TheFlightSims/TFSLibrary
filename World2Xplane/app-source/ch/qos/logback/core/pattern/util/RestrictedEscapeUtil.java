/*    */ package ch.qos.logback.core.pattern.util;
/*    */ 
/*    */ public class RestrictedEscapeUtil implements IEscapeUtil {
/*    */   public void escape(String escapeChars, StringBuffer buf, char next, int pointer) {
/* 25 */     if (escapeChars.indexOf(next) >= 0) {
/* 26 */       buf.append(next);
/*    */     } else {
/* 30 */       buf.append("\\");
/* 32 */       buf.append(next);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\patter\\util\RestrictedEscapeUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */