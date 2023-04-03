/*    */ package org.geotools.data;
/*    */ 
/*    */ class CurrentTransactionLock extends FeatureLock {
/*    */   CurrentTransactionLock() {
/* 33 */     super(null, -1L);
/*    */   }
/*    */   
/*    */   public String getAuthorization() {
/* 48 */     return toString();
/*    */   }
/*    */   
/*    */   public long getDuration() {
/* 64 */     return -1L;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     return "CURRENT_TRANSACTION";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\CurrentTransactionLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */