/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.IThrowableProxy;
/*    */ import ch.qos.logback.classic.spi.StackTraceElementProxy;
/*    */ import ch.qos.logback.classic.spi.ThrowableProxyUtil;
/*    */ import ch.qos.logback.core.CoreConstants;
/*    */ 
/*    */ public class RootCauseFirstThrowableProxyConverter extends ExtendedThrowableProxyConverter {
/*    */   protected String throwableProxyToString(IThrowableProxy tp) {
/* 29 */     StringBuilder buf = new StringBuilder(2048);
/* 30 */     subjoinRootCauseFirst(tp, buf);
/* 31 */     return buf.toString();
/*    */   }
/*    */   
/*    */   private void subjoinRootCauseFirst(IThrowableProxy tp, StringBuilder buf) {
/* 35 */     if (tp.getCause() != null)
/* 36 */       subjoinRootCauseFirst(tp.getCause(), buf); 
/* 37 */     subjoinRootCause(tp, buf);
/*    */   }
/*    */   
/*    */   private void subjoinRootCause(IThrowableProxy tp, StringBuilder buf) {
/* 41 */     ThrowableProxyUtil.subjoinFirstLineRootCauseFirst(buf, tp);
/* 42 */     buf.append(CoreConstants.LINE_SEPARATOR);
/* 43 */     StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
/* 44 */     int commonFrames = tp.getCommonFrames();
/* 46 */     boolean unrestrictedPrinting = (this.lengthOption > stepArray.length);
/* 49 */     int maxIndex = unrestrictedPrinting ? stepArray.length : this.lengthOption;
/* 50 */     if (commonFrames > 0 && unrestrictedPrinting)
/* 51 */       maxIndex -= commonFrames; 
/* 54 */     for (int i = 0; i < maxIndex; i++) {
/* 55 */       String string = stepArray[i].toString();
/* 56 */       buf.append('\t');
/* 57 */       buf.append(string);
/* 58 */       extraData(buf, stepArray[i]);
/* 59 */       buf.append(CoreConstants.LINE_SEPARATOR);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\RootCauseFirstThrowableProxyConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */