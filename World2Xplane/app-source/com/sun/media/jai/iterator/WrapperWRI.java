/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Point;
/*    */ import java.awt.image.Raster;
/*    */ import java.awt.image.TileObserver;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.awt.image.WritableRenderedImage;
/*    */ 
/*    */ public class WrapperWRI extends WrapperRI implements WritableRenderedImage {
/*    */   WritableRaster wras;
/*    */   
/*    */   public WrapperWRI(WritableRaster wras) {
/* 28 */     super(wras);
/* 29 */     this.wras = wras;
/*    */   }
/*    */   
/*    */   public void addTileObserver(TileObserver to) {
/* 33 */     throw new RuntimeException(JaiI18N.getString("WrapperWRI0"));
/*    */   }
/*    */   
/*    */   public void removeTileObserver(TileObserver to) {
/* 37 */     throw new RuntimeException(JaiI18N.getString("WrapperWRI0"));
/*    */   }
/*    */   
/*    */   public WritableRaster getWritableTile(int tileX, int tileY) {
/* 41 */     if (tileX != 0 || tileY != 0)
/* 42 */       throw new IllegalArgumentException(); 
/* 44 */     return this.wras;
/*    */   }
/*    */   
/*    */   public void releaseWritableTile(int tileX, int tileY) {
/* 48 */     if (tileX != 0 || tileY != 0)
/* 49 */       throw new IllegalArgumentException(); 
/*    */   }
/*    */   
/*    */   public boolean isTileWritable(int tileX, int tileY) {
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public Point[] getWritableTileIndices() {
/* 58 */     Point[] p = new Point[1];
/* 59 */     p[0] = new Point(0, 0);
/* 60 */     return p;
/*    */   }
/*    */   
/*    */   public boolean hasTileWriters() {
/* 64 */     return true;
/*    */   }
/*    */   
/*    */   public void setData(Raster r) {
/* 68 */     throw new RuntimeException(JaiI18N.getString("WrapperWRI0"));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\WrapperWRI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */