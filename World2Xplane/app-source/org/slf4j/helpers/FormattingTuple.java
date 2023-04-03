/*    */ package org.slf4j.helpers;
/*    */ 
/*    */ public class FormattingTuple {
/* 32 */   public static FormattingTuple NULL = new FormattingTuple(null);
/*    */   
/*    */   private String message;
/*    */   
/*    */   private Throwable throwable;
/*    */   
/*    */   private Object[] argArray;
/*    */   
/*    */   public FormattingTuple(String message) {
/* 39 */     this(message, null, null);
/*    */   }
/*    */   
/*    */   public FormattingTuple(String message, Object[] argArray, Throwable throwable) {
/* 43 */     this.message = message;
/* 44 */     this.throwable = throwable;
/* 45 */     if (throwable == null) {
/* 46 */       this.argArray = argArray;
/*    */     } else {
/* 48 */       this.argArray = trimmedCopy(argArray);
/*    */     } 
/*    */   }
/*    */   
/*    */   static Object[] trimmedCopy(Object[] argArray) {
/* 53 */     if (argArray == null || argArray.length == 0)
/* 54 */       throw new IllegalStateException("non-sensical empty or null argument array"); 
/* 56 */     int trimemdLen = argArray.length - 1;
/* 57 */     Object[] trimmed = new Object[trimemdLen];
/* 58 */     System.arraycopy(argArray, 0, trimmed, 0, trimemdLen);
/* 59 */     return trimmed;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 63 */     return this.message;
/*    */   }
/*    */   
/*    */   public Object[] getArgArray() {
/* 67 */     return this.argArray;
/*    */   }
/*    */   
/*    */   public Throwable getThrowable() {
/* 71 */     return this.throwable;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\helpers\FormattingTuple.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */