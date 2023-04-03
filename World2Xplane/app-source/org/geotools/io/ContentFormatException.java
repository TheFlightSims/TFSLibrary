/*    */ package org.geotools.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class ContentFormatException extends IOException {
/*    */   private static final long serialVersionUID = 6152194019351374599L;
/*    */   
/*    */   public ContentFormatException(String message) {
/* 48 */     super(message);
/*    */   }
/*    */   
/*    */   public ContentFormatException(String message, Throwable cause) {
/* 56 */     super(message);
/* 57 */     initCause(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\ContentFormatException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */