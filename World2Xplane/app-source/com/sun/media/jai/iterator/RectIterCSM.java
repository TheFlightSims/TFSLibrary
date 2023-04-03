/*     */ package com.sun.media.jai.iterator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import javax.media.jai.PlanarImage;
/*     */ 
/*     */ public abstract class RectIterCSM extends RectIterFallback {
/*     */   protected int[] bankIndices;
/*     */   
/*     */   protected int scanlineStride;
/*     */   
/*     */   protected int pixelStride;
/*     */   
/*     */   protected int[] bandOffsets;
/*     */   
/*     */   protected int[] DBOffsets;
/*     */   
/*     */   protected int offset;
/*     */   
/*     */   protected int bandOffset;
/*     */   
/*     */   public RectIterCSM(RenderedImage im, Rectangle bounds) {
/*  36 */     super(im, bounds);
/*  38 */     ComponentSampleModel csm = (ComponentSampleModel)this.sampleModel;
/*  40 */     this.scanlineStride = csm.getScanlineStride();
/*  41 */     this.pixelStride = csm.getPixelStride();
/*  42 */     this.bankIndices = csm.getBankIndices();
/*  43 */     int[] bo = csm.getBandOffsets();
/*  45 */     this.bandOffsets = new int[this.numBands + 1];
/*  46 */     for (int i = 0; i < this.numBands; i++)
/*  47 */       this.bandOffsets[i] = bo[i]; 
/*  49 */     this.bandOffsets[this.numBands] = 0;
/*  51 */     this.DBOffsets = new int[this.numBands];
/*  53 */     this.offset = (this.y - this.sampleModelTranslateY) * this.scanlineStride + (this.x - this.sampleModelTranslateX) * this.pixelStride;
/*  55 */     this.bandOffset = this.bandOffsets[0];
/*     */   }
/*     */   
/*     */   protected void dataBufferChanged() {}
/*     */   
/*     */   protected void adjustBandOffsets() {
/*  61 */     int[] newDBOffsets = this.dataBuffer.getOffsets();
/*  62 */     for (int i = 0; i < this.numBands; i++) {
/*  63 */       int bankNum = this.bankIndices[i];
/*  64 */       this.bandOffsets[i] = this.bandOffsets[i] + newDBOffsets[bankNum] - this.DBOffsets[bankNum];
/*     */     } 
/*  66 */     this.DBOffsets = newDBOffsets;
/*     */   }
/*     */   
/*     */   protected void setDataBuffer() {
/*  70 */     Raster tile = this.im.getTile(this.tileX, this.tileY);
/*  71 */     this.dataBuffer = tile.getDataBuffer();
/*  72 */     dataBufferChanged();
/*  74 */     int newSampleModelTranslateX = tile.getSampleModelTranslateX();
/*  75 */     int newSampleModelTranslateY = tile.getSampleModelTranslateY();
/*  77 */     int deltaX = this.sampleModelTranslateX - newSampleModelTranslateX;
/*  78 */     int deltaY = this.sampleModelTranslateY - newSampleModelTranslateY;
/*  80 */     this.offset += deltaY * this.scanlineStride + deltaX * this.pixelStride;
/*  82 */     this.sampleModelTranslateX = newSampleModelTranslateX;
/*  83 */     this.sampleModelTranslateY = newSampleModelTranslateY;
/*     */   }
/*     */   
/*     */   public void startLines() {
/*  87 */     this.offset += (this.bounds.y - this.y) * this.scanlineStride;
/*  88 */     this.y = this.bounds.y;
/*  90 */     this.tileY = this.startTileY;
/*  91 */     setTileYBounds();
/*  92 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   public void nextLine() {
/*  96 */     this.y++;
/*  97 */     this.offset += this.scanlineStride;
/*     */   }
/*     */   
/*     */   public void jumpLines(int num) {
/* 101 */     int jumpY = this.y + num;
/* 102 */     if (jumpY < this.bounds.y || jumpY > this.lastY)
/* 104 */       throw new IndexOutOfBoundsException(JaiI18N.getString("RectIterFallback1")); 
/* 107 */     this.y = jumpY;
/* 108 */     this.offset += num * this.scanlineStride;
/* 110 */     if (this.y < this.prevYBoundary || this.y > this.nextYBoundary) {
/* 111 */       this.tileY = PlanarImage.YToTileY(this.y, this.tileGridYOffset, this.tileHeight);
/* 114 */       setTileYBounds();
/* 115 */       setDataBuffer();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startPixels() {
/* 120 */     this.offset += (this.bounds.x - this.x) * this.pixelStride;
/* 121 */     this.x = this.bounds.x;
/* 123 */     this.tileX = this.startTileX;
/* 124 */     setTileXBounds();
/* 125 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   public void nextPixel() {
/* 129 */     this.x++;
/* 130 */     this.offset += this.pixelStride;
/*     */   }
/*     */   
/*     */   public void jumpPixels(int num) {
/* 134 */     int jumpX = this.x + num;
/* 135 */     if (jumpX < this.bounds.x || jumpX > this.lastX)
/* 137 */       throw new IndexOutOfBoundsException(JaiI18N.getString("RectIterFallback0")); 
/* 140 */     this.x = jumpX;
/* 141 */     this.offset += num * this.pixelStride;
/* 143 */     if (this.x < this.prevXBoundary || this.x > this.nextXBoundary) {
/* 144 */       this.tileX = PlanarImage.XToTileX(this.x, this.tileGridXOffset, this.tileWidth);
/* 148 */       setTileXBounds();
/* 149 */       setDataBuffer();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startBands() {
/* 154 */     this.b = 0;
/* 155 */     this.bandOffset = this.bandOffsets[0];
/*     */   }
/*     */   
/*     */   public void nextBand() {
/* 159 */     this.b++;
/* 160 */     this.bandOffset = this.bandOffsets[this.b];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RectIterCSM.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */