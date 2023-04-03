/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.Raster;
/*     */ import javax.media.jai.PlanarImage;
/*     */ 
/*     */ final class TileJob implements Job {
/*     */   final SunTileScheduler scheduler;
/*     */   
/*     */   final boolean isBlocking;
/*     */   
/*     */   final PlanarImage owner;
/*     */   
/*     */   final Point[] tileIndices;
/*     */   
/*     */   final Raster[] tiles;
/*     */   
/*     */   final int offset;
/*     */   
/*     */   final int numTiles;
/*     */   
/*     */   boolean done = false;
/*     */   
/* 353 */   Exception exception = null;
/*     */   
/*     */   TileJob(SunTileScheduler scheduler, boolean isBlocking, PlanarImage owner, Point[] tileIndices, Raster[] tiles, int offset, int numTiles) {
/* 360 */     this.scheduler = scheduler;
/* 361 */     this.isBlocking = isBlocking;
/* 362 */     this.owner = owner;
/* 363 */     this.tileIndices = tileIndices;
/* 364 */     this.tiles = tiles;
/* 365 */     this.offset = offset;
/* 366 */     this.numTiles = numTiles;
/*     */   }
/*     */   
/*     */   public void compute() {
/* 373 */     this.exception = this.scheduler.compute(this.owner, this.tileIndices, this.tiles, this.offset, this.numTiles, null);
/* 375 */     this.done = true;
/*     */   }
/*     */   
/*     */   public boolean notDone() {
/* 383 */     return !this.done;
/*     */   }
/*     */   
/*     */   public PlanarImage getOwner() {
/* 388 */     return this.owner;
/*     */   }
/*     */   
/*     */   public boolean isBlocking() {
/* 393 */     return this.isBlocking;
/*     */   }
/*     */   
/*     */   public Exception getException() {
/* 398 */     return this.exception;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\TileJob.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */