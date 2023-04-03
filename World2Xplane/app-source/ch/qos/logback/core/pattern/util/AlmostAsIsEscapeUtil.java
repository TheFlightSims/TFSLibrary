/*    */ package ch.qos.logback.core.pattern.util;
/*    */ 
/*    */ public class AlmostAsIsEscapeUtil extends RestrictedEscapeUtil {
/*    */   public void escape(String escapeChars, StringBuffer buf, char next, int pointer) {
/* 43 */     super.escape("%)", buf, next, pointer);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\patter\\util\AlmostAsIsEscapeUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */