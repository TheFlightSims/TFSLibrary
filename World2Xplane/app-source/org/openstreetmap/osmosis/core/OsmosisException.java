/*    */ package org.openstreetmap.osmosis.core;
/*    */ 
/*    */ public abstract class OsmosisException extends Exception {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public OsmosisException() {}
/*    */   
/*    */   public OsmosisException(String message) {
/* 32 */     super(message);
/*    */   }
/*    */   
/*    */   public OsmosisException(Throwable cause) {
/* 44 */     super(cause);
/*    */   }
/*    */   
/*    */   public OsmosisException(String message, Throwable cause) {
/* 56 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\OsmosisException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */