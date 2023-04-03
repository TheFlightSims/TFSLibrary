/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class NoSuchIndexElementException extends OsmosisRuntimeException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public NoSuchIndexElementException() {}
/*    */   
/*    */   public NoSuchIndexElementException(String message) {
/* 33 */     super(message);
/*    */   }
/*    */   
/*    */   public NoSuchIndexElementException(Throwable cause) {
/* 45 */     super(cause);
/*    */   }
/*    */   
/*    */   public NoSuchIndexElementException(String message, Throwable cause) {
/* 57 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\NoSuchIndexElementException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */