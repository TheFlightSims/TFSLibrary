/*    */ package ch.qos.logback.core.pattern.util;
/*    */ 
/*    */ public class AsIsEscapeUtil implements IEscapeUtil {
/*    */   public void escape(String escapeChars, StringBuffer buf, char next, int pointer) {
/* 31 */     buf.append("\\");
/* 33 */     buf.append(next);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\patter\\util\AsIsEscapeUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */