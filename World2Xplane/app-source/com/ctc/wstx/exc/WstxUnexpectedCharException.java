/*    */ package com.ctc.wstx.exc;
/*    */ 
/*    */ import javax.xml.stream.Location;
/*    */ 
/*    */ public class WstxUnexpectedCharException extends WstxParsingException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   final char mChar;
/*    */   
/*    */   public WstxUnexpectedCharException(String msg, Location loc, char c) {
/* 19 */     super(msg, loc);
/* 20 */     this.mChar = c;
/*    */   }
/*    */   
/*    */   public char getChar() {
/* 24 */     return this.mChar;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\exc\WstxUnexpectedCharException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */