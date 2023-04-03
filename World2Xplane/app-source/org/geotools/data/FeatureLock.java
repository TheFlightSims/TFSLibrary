/*    */ package org.geotools.data;
/*    */ 
/*    */ public class FeatureLock {
/* 47 */   public static final FeatureLock TRANSACTION = new CurrentTransactionLock();
/*    */   
/*    */   protected String authorization;
/*    */   
/*    */   protected long duration;
/*    */   
/*    */   public FeatureLock(String authorization, long duration) {
/* 58 */     this.authorization = authorization;
/* 59 */     this.duration = duration;
/*    */   }
/*    */   
/*    */   public String getAuthorization() {
/* 68 */     return this.authorization;
/*    */   }
/*    */   
/*    */   public long getDuration() {
/* 77 */     return this.duration;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */