/*    */ package com.ctc.wstx.exc;
/*    */ 
/*    */ import javax.xml.stream.Location;
/*    */ 
/*    */ public class WstxEOFException extends WstxParsingException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public WstxEOFException(String msg, Location loc) {
/* 15 */     super(msg, loc);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\exc\WstxEOFException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */