/*    */ package org.apache.commons.lang;
/*    */ 
/*    */ public class NullArgumentException extends IllegalArgumentException {
/*    */   private static final long serialVersionUID = 1174360235354917591L;
/*    */   
/*    */   public NullArgumentException(String argName) {
/* 61 */     super(((argName == null) ? "Argument" : argName) + " must not be null.");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\NullArgumentException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */