/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.Raster;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.TileComputationListener;
/*     */ import javax.media.jai.TileRequest;
/*     */ 
/*     */ final class RequestJob implements Job {
/*     */   final SunTileScheduler scheduler;
/*     */   
/*     */   final PlanarImage owner;
/*     */   
/*     */   final int tileX;
/*     */   
/*     */   final int tileY;
/*     */   
/*     */   final Raster[] tiles;
/*     */   
/*     */   final int offset;
/*     */   
/*     */   boolean done = false;
/*     */   
/* 201 */   Exception exception = null;
/*     */   
/*     */   RequestJob(SunTileScheduler scheduler, PlanarImage owner, int tileX, int tileY, Raster[] tiles, int offset) {
/* 208 */     this.scheduler = scheduler;
/* 209 */     this.owner = owner;
/* 210 */     this.tileX = tileX;
/* 211 */     this.tileY = tileY;
/* 212 */     this.tiles = tiles;
/* 213 */     this.offset = offset;
/*     */   }
/*     */   
/*     */   public void compute() {
/*     */     List reqList;
/* 222 */     synchronized (this.scheduler.tileRequests) {
/* 224 */       Object tileID = SunTileScheduler.tileKey(this.owner, this.tileX, this.tileY);
/* 227 */       reqList = (List)this.scheduler.tileRequests.remove(tileID);
/* 230 */       this.scheduler.tileJobs.remove(tileID);
/*     */     } 
/* 236 */     if (reqList != null && !reqList.isEmpty()) {
/* 238 */       Point p = new Point(this.tileX, this.tileY);
/* 239 */       Integer tileStatus = new Integer(1);
/* 240 */       Iterator reqIter = reqList.iterator();
/* 241 */       while (reqIter.hasNext()) {
/* 242 */         Request r = reqIter.next();
/* 243 */         r.tileStatus.put(p, tileStatus);
/*     */       } 
/*     */       try {
/* 247 */         this.tiles[this.offset] = this.owner.getTile(this.tileX, this.tileY);
/* 248 */       } catch (Exception e) {
/* 249 */         this.exception = e;
/*     */       } finally {
/* 252 */         int numReq = reqList.size();
/* 253 */         Set listeners = SunTileScheduler.getListeners(reqList);
/* 256 */         if (listeners != null && !listeners.isEmpty()) {
/* 258 */           TileRequest[] requests = (TileRequest[])reqList.toArray((Object[])new TileRequest[0]);
/* 262 */           tileStatus = new Integer((this.exception == null) ? 2 : 4);
/* 265 */           for (int i = 0; i < numReq; i++)
/* 266 */             ((Request)requests[i]).tileStatus.put(p, tileStatus); 
/* 270 */           Iterator iter = listeners.iterator();
/* 273 */           if (this.exception == null) {
/* 275 */             while (iter.hasNext()) {
/* 276 */               TileComputationListener listener = iter.next();
/* 278 */               listener.tileComputed(this.scheduler, requests, this.owner, this.tileX, this.tileY, this.tiles[this.offset]);
/*     */             } 
/*     */           } else {
/* 284 */             while (iter.hasNext()) {
/* 285 */               TileComputationListener listener = iter.next();
/* 287 */               listener.tileComputationFailure(this.scheduler, requests, this.owner, this.tileX, this.tileY, this.exception);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 297 */     this.done = true;
/*     */   }
/*     */   
/*     */   public boolean notDone() {
/* 305 */     return !this.done;
/*     */   }
/*     */   
/*     */   public PlanarImage getOwner() {
/* 310 */     return this.owner;
/*     */   }
/*     */   
/*     */   public boolean isBlocking() {
/* 315 */     return false;
/*     */   }
/*     */   
/*     */   public Exception getException() {
/* 320 */     return this.exception;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 325 */     String tString = "null";
/* 326 */     if (this.tiles[this.offset] != null)
/* 327 */       tString = this.tiles[this.offset].toString(); 
/* 329 */     return getClass().getName() + "@" + Integer.toHexString(hashCode()) + ": owner = " + this.owner.toString() + " tileX = " + Integer.toString(this.tileX) + " tileY = " + Integer.toString(this.tileY) + " tile = " + tString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\RequestJob.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */