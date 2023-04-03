/*    */ package org.geotools.data.shapefile.index;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class TreeException extends IOException {
/*    */   private static final long serialVersionUID = 1988241322009839486L;
/*    */   
/*    */   public TreeException() {}
/*    */   
/*    */   public TreeException(String message) {
/* 43 */     super(message);
/*    */   }
/*    */   
/*    */   public TreeException(String message, Throwable cause) {
/* 53 */     super(message);
/* 54 */     initCause(cause);
/*    */   }
/*    */   
/*    */   public TreeException(Throwable cause) {
/* 64 */     initCause(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\TreeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */