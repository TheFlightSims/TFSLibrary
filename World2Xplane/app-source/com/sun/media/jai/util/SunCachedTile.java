/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.math.BigInteger;
/*     */ import javax.media.jai.CachedTile;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.remote.SerializableRenderedImage;
/*     */ 
/*     */ final class SunCachedTile implements CachedTile {
/*     */   Raster tile;
/*     */   
/*     */   WeakReference owner;
/*     */   
/*     */   int tileX;
/*     */   
/*     */   int tileY;
/*     */   
/*     */   Object tileCacheMetric;
/*     */   
/*     */   long timeStamp;
/*     */   
/*     */   Object key;
/*     */   
/*     */   long memorySize;
/*     */   
/*     */   SunCachedTile previous;
/*     */   
/*     */   SunCachedTile next;
/*     */   
/*  57 */   int action = 0;
/*     */   
/*     */   SunCachedTile(RenderedImage owner, int tileX, int tileY, Raster tile, Object tileCacheMetric) {
/*  70 */     this.owner = new WeakReference(owner);
/*  71 */     this.tile = tile;
/*  72 */     this.tileX = tileX;
/*  73 */     this.tileY = tileY;
/*  75 */     this.tileCacheMetric = tileCacheMetric;
/*  77 */     this.key = hashKey(owner, tileX, tileY);
/*  80 */     DataBuffer db = tile.getDataBuffer();
/*  81 */     this.memorySize = DataBuffer.getDataTypeSize(db.getDataType()) / 8L * db.getSize() * db.getNumBanks();
/*     */   }
/*     */   
/*     */   static Object hashKey(RenderedImage owner, int tileX, int tileY) {
/*  98 */     long idx = tileY * owner.getNumXTiles() + tileX;
/* 100 */     BigInteger imageID = null;
/* 101 */     if (owner instanceof PlanarImage) {
/* 102 */       imageID = (BigInteger)((PlanarImage)owner).getImageID();
/* 103 */     } else if (owner instanceof SerializableRenderedImage) {
/* 104 */       imageID = (BigInteger)((SerializableRenderedImage)owner).getImageID();
/*     */     } 
/* 106 */     if (imageID != null) {
/* 107 */       byte[] buf = imageID.toByteArray();
/* 108 */       int length = buf.length;
/* 109 */       byte[] buf1 = new byte[length + 8];
/* 110 */       System.arraycopy(buf, 0, buf1, 0, length);
/* 111 */       for (int i = 7, j = 0; i >= 0; i--, j += 8)
/* 112 */         buf1[length++] = (byte)(int)(idx >> j); 
/* 113 */       return new BigInteger(buf1);
/*     */     } 
/* 116 */     idx &= 0xFFFFFFFFL;
/* 117 */     return new Long(owner.hashCode() << 32L | idx);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 143 */     RenderedImage o = getOwner();
/* 144 */     String ostring = (o == null) ? "null" : o.toString();
/* 146 */     Raster t = getTile();
/* 147 */     String tstring = (t == null) ? "null" : t.toString();
/* 149 */     return getClass().getName() + "@" + Integer.toHexString(hashCode()) + ": owner = " + ostring + " tileX = " + Integer.toString(this.tileX) + " tileY = " + Integer.toString(this.tileY) + " tile = " + tstring + " key = " + ((this.key instanceof Long) ? Long.toHexString(((Long)this.key).longValue()) : this.key.toString()) + " memorySize = " + Long.toString(this.memorySize) + " timeStamp = " + Long.toString(this.timeStamp);
/*     */   }
/*     */   
/*     */   public Raster getTile() {
/* 161 */     return this.tile;
/*     */   }
/*     */   
/*     */   public RenderedImage getOwner() {
/* 166 */     return this.owner.get();
/*     */   }
/*     */   
/*     */   public long getTileTimeStamp() {
/* 171 */     return this.timeStamp;
/*     */   }
/*     */   
/*     */   public Object getTileCacheMetric() {
/* 176 */     return this.tileCacheMetric;
/*     */   }
/*     */   
/*     */   public long getTileSize() {
/* 181 */     return this.memorySize;
/*     */   }
/*     */   
/*     */   public int getAction() {
/* 188 */     return this.action;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\SunCachedTile.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */