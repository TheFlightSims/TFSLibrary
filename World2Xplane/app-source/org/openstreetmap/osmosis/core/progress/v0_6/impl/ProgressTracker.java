/*    */ package org.openstreetmap.osmosis.core.progress.v0_6.impl;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class ProgressTracker {
/*    */   private int interval;
/*    */   
/*    */   private boolean initialized;
/*    */   
/*    */   private long lastUpdateTimestamp;
/*    */   
/*    */   private long objectCount;
/*    */   
/*    */   private double objectsPerSecond;
/*    */   
/*    */   public ProgressTracker(int interval) {
/* 28 */     this.interval = interval;
/* 30 */     this.initialized = false;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 38 */     this.lastUpdateTimestamp = System.currentTimeMillis();
/* 39 */     this.objectCount = 0L;
/* 40 */     this.objectsPerSecond = 0.0D;
/* 42 */     this.initialized = true;
/*    */   }
/*    */   
/*    */   public boolean updateRequired() {
/* 56 */     if (!this.initialized)
/* 57 */       throw new OsmosisRuntimeException("initialize has not been called"); 
/* 62 */     long currentTimestamp = System.currentTimeMillis();
/* 63 */     long duration = currentTimestamp - this.lastUpdateTimestamp;
/* 66 */     this.objectCount++;
/* 68 */     if (duration > this.interval || duration < 0L) {
/* 69 */       this.lastUpdateTimestamp = currentTimestamp;
/* 72 */       this.objectsPerSecond = this.objectCount * 1000.0D / duration;
/* 75 */       this.objectCount = 0L;
/* 77 */       return true;
/*    */     } 
/* 80 */     return false;
/*    */   }
/*    */   
/*    */   public double getObjectsPerSecond() {
/* 93 */     return this.objectsPerSecond;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\progress\v0_6\impl\ProgressTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */