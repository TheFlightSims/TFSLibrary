/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class EndOfStoreException extends OsmosisRuntimeException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public EndOfStoreException() {}
/*    */   
/*    */   public EndOfStoreException(String message) {
/* 33 */     super(message);
/*    */   }
/*    */   
/*    */   public EndOfStoreException(Throwable cause) {
/* 45 */     super(cause);
/*    */   }
/*    */   
/*    */   public EndOfStoreException(String message, Throwable cause) {
/* 57 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\EndOfStoreException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */