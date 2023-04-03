/*    */ package org.geotools.data.shapefile.index.quadtree;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class StoreException extends IOException {
/*    */   private static final long serialVersionUID = -3356954193373344773L;
/*    */   
/*    */   public StoreException() {}
/*    */   
/*    */   public StoreException(String message) {
/* 38 */     super(message);
/*    */   }
/*    */   
/*    */   public StoreException(Throwable cause) {
/* 43 */     initCause(cause);
/*    */   }
/*    */   
/*    */   public StoreException(String message, Throwable cause) {
/* 47 */     super(message);
/* 48 */     initCause(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\quadtree\StoreException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */