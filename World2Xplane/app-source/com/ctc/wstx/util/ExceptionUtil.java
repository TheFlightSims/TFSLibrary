/*    */ package com.ctc.wstx.util;
/*    */ 
/*    */ public final class ExceptionUtil {
/*    */   public static void throwRuntimeException(Throwable t) {
/* 14 */     throwIfUnchecked(t);
/* 16 */     RuntimeException rex = new RuntimeException("[was " + t.getClass() + "] " + t.getMessage());
/* 18 */     setInitCause(rex, t);
/* 19 */     throw rex;
/*    */   }
/*    */   
/*    */   public static void throwAsIllegalArgument(Throwable t) {
/* 25 */     throwIfUnchecked(t);
/* 27 */     IllegalArgumentException rex = new IllegalArgumentException("[was " + t.getClass() + "] " + t.getMessage());
/* 29 */     setInitCause(rex, t);
/* 30 */     throw rex;
/*    */   }
/*    */   
/*    */   public static void throwIfUnchecked(Throwable t) {
/* 36 */     if (t instanceof RuntimeException)
/* 37 */       throw (RuntimeException)t; 
/* 39 */     if (t instanceof Error)
/* 40 */       throw (Error)t; 
/*    */   }
/*    */   
/*    */   public static void throwGenericInternal() {
/* 51 */     throwInternal(null);
/*    */   }
/*    */   
/*    */   public static void throwInternal(String msg) {
/* 56 */     if (msg == null)
/* 57 */       msg = "[no description]"; 
/* 59 */     throw new RuntimeException("Internal error: " + msg);
/*    */   }
/*    */   
/*    */   public static void setInitCause(Throwable newT, Throwable rootT) {
/* 67 */     if (newT.getCause() == null)
/* 68 */       newT.initCause(rootT); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\ExceptionUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */