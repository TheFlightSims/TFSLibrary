/*    */ package org.openstreetmap.osmosis.core;
/*    */ 
/*    */ public class OsmosisRuntimeException extends RuntimeException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public OsmosisRuntimeException() {}
/*    */   
/*    */   public OsmosisRuntimeException(String message) {
/* 32 */     super(message);
/*    */   }
/*    */   
/*    */   public OsmosisRuntimeException(Throwable cause) {
/* 44 */     super(cause);
/*    */   }
/*    */   
/*    */   public OsmosisRuntimeException(String message, Throwable cause) {
/* 56 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\OsmosisRuntimeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */