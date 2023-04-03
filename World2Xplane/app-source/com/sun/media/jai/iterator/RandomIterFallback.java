/*     */ package com.sun.media.jai.iterator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.iterator.RandomIter;
/*     */ 
/*     */ public class RandomIterFallback implements RandomIter {
/*     */   protected RenderedImage im;
/*     */   
/*     */   protected Rectangle boundsRect;
/*     */   
/*     */   protected SampleModel sampleModel;
/*     */   
/*     */   protected int xID;
/*     */   
/*     */   protected int yID;
/*     */   
/*     */   protected int sampleModelTranslateX;
/*     */   
/*     */   protected int sampleModelTranslateY;
/*     */   
/*  35 */   protected DataBuffer dataBuffer = null;
/*     */   
/*     */   protected int boundsX;
/*     */   
/*     */   protected int boundsY;
/*     */   
/*     */   protected int[] xTiles;
/*     */   
/*     */   protected int[] yTiles;
/*     */   
/*     */   public RandomIterFallback(RenderedImage im, Rectangle bounds) {
/*  44 */     this.im = im;
/*  46 */     Rectangle imBounds = new Rectangle(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight());
/*  48 */     this.boundsRect = imBounds.intersection(bounds);
/*  49 */     this.sampleModel = im.getSampleModel();
/*  51 */     int x = this.boundsRect.x;
/*  52 */     int y = this.boundsRect.y;
/*  53 */     int width = this.boundsRect.width;
/*  54 */     int height = this.boundsRect.height;
/*  56 */     this.boundsX = this.boundsRect.x;
/*  57 */     this.boundsY = this.boundsRect.y;
/*  58 */     this.xTiles = new int[width];
/*  59 */     this.yTiles = new int[height];
/*  61 */     int tileWidth = im.getTileWidth();
/*  62 */     int tileGridXOffset = im.getTileGridXOffset();
/*  63 */     int minTileX = PlanarImage.XToTileX(x, tileGridXOffset, tileWidth);
/*  64 */     int offsetX = x - PlanarImage.tileXToX(minTileX, tileGridXOffset, tileWidth);
/*  66 */     int tileX = minTileX;
/*  68 */     for (int i = 0; i < width; i++) {
/*  69 */       this.xTiles[i] = tileX;
/*  70 */       offsetX++;
/*  71 */       if (offsetX == tileWidth) {
/*  72 */         tileX++;
/*  73 */         offsetX = 0;
/*     */       } 
/*     */     } 
/*  77 */     int tileHeight = im.getTileHeight();
/*  78 */     int tileGridYOffset = im.getTileGridYOffset();
/*  79 */     int minTileY = PlanarImage.YToTileY(y, tileGridYOffset, tileHeight);
/*  80 */     int offsetY = y - PlanarImage.tileYToY(minTileY, tileGridYOffset, tileHeight);
/*  82 */     int tileY = minTileY;
/*  84 */     for (int j = 0; j < height; j++) {
/*  85 */       this.yTiles[j] = tileY;
/*  86 */       offsetY++;
/*  87 */       if (offsetY == tileHeight) {
/*  88 */         tileY++;
/*  89 */         offsetY = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void makeCurrent(int xLocal, int yLocal) {
/* 102 */     int xIDNew = this.xTiles[xLocal];
/* 103 */     int yIDNew = this.yTiles[yLocal];
/* 105 */     if (xIDNew != this.xID || yIDNew != this.yID || this.dataBuffer == null) {
/* 106 */       this.xID = xIDNew;
/* 107 */       this.yID = yIDNew;
/* 108 */       Raster tile = this.im.getTile(this.xID, this.yID);
/* 110 */       this.dataBuffer = tile.getDataBuffer();
/* 111 */       this.sampleModelTranslateX = tile.getSampleModelTranslateX();
/* 112 */       this.sampleModelTranslateY = tile.getSampleModelTranslateY();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSample(int x, int y, int b) {
/* 117 */     makeCurrent(x - this.boundsX, y - this.boundsY);
/* 118 */     return this.sampleModel.getSample(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public float getSampleFloat(int x, int y, int b) {
/* 125 */     makeCurrent(x - this.boundsX, y - this.boundsY);
/* 126 */     return this.sampleModel.getSampleFloat(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public double getSampleDouble(int x, int y, int b) {
/* 133 */     makeCurrent(x - this.boundsX, y - this.boundsY);
/* 134 */     return this.sampleModel.getSampleDouble(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public int[] getPixel(int x, int y, int[] iArray) {
/* 141 */     makeCurrent(x - this.boundsX, y - this.boundsY);
/* 142 */     return this.sampleModel.getPixel(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, iArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public float[] getPixel(int x, int y, float[] fArray) {
/* 149 */     makeCurrent(x - this.boundsX, y - this.boundsY);
/* 150 */     return this.sampleModel.getPixel(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, fArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public double[] getPixel(int x, int y, double[] dArray) {
/* 157 */     makeCurrent(x - this.boundsX, y - this.boundsY);
/* 158 */     return this.sampleModel.getPixel(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, dArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public void done() {
/* 165 */     this.xTiles = null;
/* 166 */     this.yTiles = null;
/* 167 */     this.dataBuffer = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RandomIterFallback.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */