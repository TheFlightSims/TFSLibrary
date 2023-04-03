/*    */ package com.ctc.wstx.exc;
/*    */ 
/*    */ import javax.xml.stream.Location;
/*    */ 
/*    */ public class WstxParsingException extends WstxException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public WstxParsingException(String msg, Location loc) {
/* 14 */     super(msg, loc);
/*    */   }
/*    */   
/*    */   public WstxParsingException(String msg) {
/* 18 */     super(msg);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\exc\WstxParsingException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */