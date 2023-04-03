/*    */ package org.opengis.util;
/*    */ 
/*    */ @Deprecated
/*    */ public class NoSuchEnumerationException extends Exception {
/*    */   private static final long serialVersionUID = 5827953825646995065L;
/*    */   
/*    */   private final int value;
/*    */   
/*    */   public NoSuchEnumerationException(int value) {
/* 44 */     super("No enumeration exists for the value " + value);
/* 45 */     this.value = value;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 52 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\NoSuchEnumerationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */