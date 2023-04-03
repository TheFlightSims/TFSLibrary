/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.TileComputationListener;
/*     */ import javax.media.jai.TileRequest;
/*     */ import javax.media.jai.TileScheduler;
/*     */ 
/*     */ class Request implements TileRequest {
/*     */   private final TileScheduler scheduler;
/*     */   
/*     */   final PlanarImage image;
/*     */   
/*     */   final List indices;
/*     */   
/*     */   final Set listeners;
/*     */   
/*     */   final Hashtable tileStatus;
/*     */   
/*     */   Request(TileScheduler scheduler, PlanarImage image, Point[] tileIndices, TileComputationListener[] tileListeners) {
/*  82 */     if (scheduler == null)
/*  83 */       throw new IllegalArgumentException(); 
/*  85 */     this.scheduler = scheduler;
/*  88 */     if (image == null)
/*  89 */       throw new IllegalArgumentException(); 
/*  91 */     this.image = image;
/*  94 */     if (tileIndices == null || tileIndices.length == 0)
/*  96 */       throw new IllegalArgumentException(); 
/* 100 */     this.indices = Arrays.asList(tileIndices);
/* 103 */     if (tileListeners != null) {
/* 104 */       int numListeners = tileListeners.length;
/* 105 */       if (numListeners > 0) {
/* 106 */         this.listeners = new HashSet(numListeners);
/* 107 */         for (int i = 0; i < numListeners; i++)
/* 108 */           this.listeners.add(tileListeners[i]); 
/*     */       } else {
/* 111 */         this.listeners = null;
/*     */       } 
/*     */     } else {
/* 114 */       this.listeners = null;
/*     */     } 
/* 118 */     this.tileStatus = new Hashtable(tileIndices.length);
/*     */   }
/*     */   
/*     */   public PlanarImage getImage() {
/* 124 */     return this.image;
/*     */   }
/*     */   
/*     */   public Point[] getTileIndices() {
/* 128 */     return (Point[])this.indices.toArray((Object[])new Point[0]);
/*     */   }
/*     */   
/*     */   public TileComputationListener[] getTileListeners() {
/* 132 */     return (TileComputationListener[])this.listeners.toArray((Object[])new TileComputationListener[0]);
/*     */   }
/*     */   
/*     */   public boolean isStatusAvailable() {
/* 137 */     return true;
/*     */   }
/*     */   
/*     */   public int getTileStatus(int tileX, int tileY) {
/*     */     int status;
/* 141 */     Point p = new Point(tileX, tileY);
/* 144 */     if (this.tileStatus.containsKey(p)) {
/* 145 */       status = ((Integer)this.tileStatus.get(p)).intValue();
/*     */     } else {
/* 147 */       status = 0;
/*     */     } 
/* 150 */     return status;
/*     */   }
/*     */   
/*     */   public void cancelTiles(Point[] tileIndices) {
/* 155 */     this.scheduler.cancelTiles(this, tileIndices);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\Request.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */