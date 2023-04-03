/*    */ package ch.qos.logback.core.status;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class OnErrorConsoleStatusListener extends OnPrintStreamStatusListenerBase {
/*    */   protected PrintStream getPrintStream() {
/* 28 */     return System.err;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\status\OnErrorConsoleStatusListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */