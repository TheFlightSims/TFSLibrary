/*     */ package com.sun.media.jai.iterator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class WrapperRI implements RenderedImage {
/*     */   Raster ras;
/*     */   
/*     */   public WrapperRI(Raster ras) {
/*  30 */     this.ras = ras;
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/*  34 */     return null;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  38 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/*  42 */     return null;
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/*  46 */     return null;
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/*  50 */     return this.ras.getSampleModel();
/*     */   }
/*     */   
/*     */   public int getWidth() {
/*  54 */     return this.ras.getWidth();
/*     */   }
/*     */   
/*     */   public int getHeight() {
/*  58 */     return this.ras.getHeight();
/*     */   }
/*     */   
/*     */   public int getMinX() {
/*  62 */     return this.ras.getMinX();
/*     */   }
/*     */   
/*     */   public int getMinY() {
/*  66 */     return this.ras.getMinY();
/*     */   }
/*     */   
/*     */   public int getNumXTiles() {
/*  70 */     return 1;
/*     */   }
/*     */   
/*     */   public int getNumYTiles() {
/*  74 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMinTileX() {
/*  78 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMinTileY() {
/*  82 */     return 0;
/*     */   }
/*     */   
/*     */   public int getTileWidth() {
/*  86 */     return this.ras.getWidth();
/*     */   }
/*     */   
/*     */   public int getTileHeight() {
/*  90 */     return this.ras.getHeight();
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset() {
/*  94 */     return this.ras.getMinX();
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset() {
/*  98 */     return this.ras.getMinY();
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 102 */     return this.ras;
/*     */   }
/*     */   
/*     */   public Raster getData() {
/* 106 */     throw new RuntimeException(JaiI18N.getString("WrapperRI0"));
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/* 110 */     throw new RuntimeException(JaiI18N.getString("WrapperRI0"));
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster raster) {
/* 114 */     throw new RuntimeException(JaiI18N.getString("WrapperRI0"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\WrapperRI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */