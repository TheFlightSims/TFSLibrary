/*    */ package com.ctc.wstx.exc;
/*    */ 
/*    */ import com.ctc.wstx.util.ExceptionUtil;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ 
/*    */ public class WstxLazyException extends RuntimeException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   final XMLStreamException mOrig;
/*    */   
/*    */   public WstxLazyException(XMLStreamException origEx) {
/* 36 */     super(origEx.getMessage());
/* 37 */     this.mOrig = origEx;
/* 39 */     ExceptionUtil.setInitCause(this, origEx);
/*    */   }
/*    */   
/*    */   public static void throwLazily(XMLStreamException ex) throws WstxLazyException {
/* 45 */     throw new WstxLazyException(ex);
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 54 */     return "[" + getClass().getName() + "] " + this.mOrig.getMessage();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 59 */     return "[" + getClass().getName() + "] " + this.mOrig.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\exc\WstxLazyException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */