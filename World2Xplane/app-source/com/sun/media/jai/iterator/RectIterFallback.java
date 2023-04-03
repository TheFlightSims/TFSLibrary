/*     */ package com.sun.media.jai.iterator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.iterator.RectIter;
/*     */ 
/*     */ public class RectIterFallback implements RectIter {
/*     */   protected RenderedImage im;
/*     */   
/*     */   protected Rectangle bounds;
/*     */   
/*     */   protected SampleModel sampleModel;
/*     */   
/*     */   protected int numBands;
/*     */   
/*     */   protected int tileWidth;
/*     */   
/*     */   protected int tileHeight;
/*     */   
/*     */   protected int tileGridXOffset;
/*     */   
/*     */   protected int tileGridYOffset;
/*     */   
/*     */   protected int startTileX;
/*     */   
/*     */   protected int startTileY;
/*     */   
/*     */   protected int tileXStart;
/*     */   
/*     */   protected int tileXEnd;
/*     */   
/*     */   protected int tileYStart;
/*     */   
/*     */   protected int tileYEnd;
/*     */   
/*     */   protected int prevXBoundary;
/*     */   
/*     */   protected int nextXBoundary;
/*     */   
/*     */   protected int prevYBoundary;
/*     */   
/*     */   protected int nextYBoundary;
/*     */   
/*     */   protected int tileX;
/*     */   
/*     */   protected int tileY;
/*     */   
/*     */   protected int lastX;
/*     */   
/*     */   protected int lastY;
/*     */   
/*     */   protected int x;
/*     */   
/*     */   protected int y;
/*     */   
/*     */   protected int localX;
/*     */   
/*     */   protected int localY;
/*     */   
/* 105 */   protected int sampleModelTranslateX = 0;
/*     */   
/* 108 */   protected int sampleModelTranslateY = 0;
/*     */   
/*     */   protected int b;
/*     */   
/* 114 */   protected DataBuffer dataBuffer = null;
/*     */   
/*     */   public RectIterFallback(RenderedImage im, Rectangle bounds) {
/* 117 */     this.im = im;
/* 118 */     this.bounds = bounds;
/* 120 */     this.sampleModel = im.getSampleModel();
/* 121 */     this.numBands = this.sampleModel.getNumBands();
/* 123 */     this.tileGridXOffset = im.getTileGridXOffset();
/* 124 */     this.tileGridYOffset = im.getTileGridYOffset();
/* 125 */     this.tileWidth = im.getTileWidth();
/* 126 */     this.tileHeight = im.getTileHeight();
/* 128 */     this.startTileX = PlanarImage.XToTileX(bounds.x, this.tileGridXOffset, this.tileWidth);
/* 131 */     this.startTileY = PlanarImage.YToTileY(bounds.y, this.tileGridYOffset, this.tileHeight);
/* 135 */     this.tileX = this.startTileX;
/* 136 */     this.tileY = this.startTileY;
/* 138 */     this.lastX = bounds.x + bounds.width - 1;
/* 139 */     this.lastY = bounds.y + bounds.height - 1;
/* 141 */     this.localX = this.x = bounds.x;
/* 142 */     this.localY = this.y = bounds.y;
/* 143 */     this.b = 0;
/* 145 */     setTileXBounds();
/* 146 */     setTileYBounds();
/* 147 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   protected final void setTileXBounds() {
/* 151 */     this.tileXStart = this.tileX * this.tileWidth + this.tileGridXOffset;
/* 152 */     this.tileXEnd = this.tileXStart + this.tileWidth - 1;
/* 154 */     this.prevXBoundary = Math.max(this.tileXStart, this.bounds.x);
/* 155 */     this.nextXBoundary = Math.min(this.tileXEnd, this.lastX);
/*     */   }
/*     */   
/*     */   protected final void setTileYBounds() {
/* 159 */     this.tileYStart = this.tileY * this.tileHeight + this.tileGridYOffset;
/* 160 */     this.tileYEnd = this.tileYStart + this.tileHeight - 1;
/* 162 */     this.prevYBoundary = Math.max(this.tileYStart, this.bounds.y);
/* 163 */     this.nextYBoundary = Math.min(this.tileYEnd, this.lastY);
/*     */   }
/*     */   
/*     */   protected void setDataBuffer() {
/* 167 */     Raster tile = this.im.getTile(this.tileX, this.tileY);
/* 168 */     this.dataBuffer = tile.getDataBuffer();
/* 170 */     int newSampleModelTranslateX = tile.getSampleModelTranslateX();
/* 171 */     int newSampleModelTranslateY = tile.getSampleModelTranslateY();
/* 172 */     this.localX += this.sampleModelTranslateX - newSampleModelTranslateX;
/* 173 */     this.localY += this.sampleModelTranslateY - newSampleModelTranslateY;
/* 175 */     this.sampleModelTranslateX = newSampleModelTranslateX;
/* 176 */     this.sampleModelTranslateY = newSampleModelTranslateY;
/*     */   }
/*     */   
/*     */   public void startLines() {
/* 180 */     this.y = this.bounds.y;
/* 181 */     this.localY = this.y - this.sampleModelTranslateY;
/* 182 */     this.tileY = this.startTileY;
/* 183 */     setTileYBounds();
/* 184 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   public void nextLine() {
/* 188 */     this.y++;
/* 189 */     this.localY++;
/*     */   }
/*     */   
/*     */   public void jumpLines(int num) {
/* 193 */     int jumpY = this.y + num;
/* 194 */     if (jumpY < this.bounds.y || jumpY > this.lastY)
/* 196 */       throw new IndexOutOfBoundsException(JaiI18N.getString("RectIterFallback1")); 
/* 199 */     this.y = jumpY;
/* 200 */     this.localY += num;
/* 202 */     if (this.y < this.prevYBoundary || this.y > this.nextYBoundary) {
/* 203 */       this.tileY = PlanarImage.YToTileY(this.y, this.tileGridYOffset, this.tileHeight);
/* 206 */       setTileYBounds();
/* 207 */       setDataBuffer();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean finishedLines() {
/* 212 */     if (this.y > this.nextYBoundary) {
/* 213 */       if (this.y > this.lastY)
/* 214 */         return true; 
/* 216 */       this.tileY++;
/* 217 */       this.tileYStart += this.tileHeight;
/* 218 */       this.tileYEnd += this.tileHeight;
/* 219 */       this.prevYBoundary = Math.max(this.tileYStart, this.bounds.y);
/* 220 */       this.nextYBoundary = Math.min(this.tileYEnd, this.lastY);
/* 222 */       setDataBuffer();
/* 223 */       return false;
/*     */     } 
/* 226 */     return false;
/*     */   }
/*     */   
/*     */   public boolean nextLineDone() {
/* 231 */     nextLine();
/* 232 */     return finishedLines();
/*     */   }
/*     */   
/*     */   public void startPixels() {
/* 236 */     this.x = this.bounds.x;
/* 237 */     this.localX = this.x - this.sampleModelTranslateX;
/* 238 */     this.tileX = this.startTileX;
/* 239 */     setTileXBounds();
/* 240 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   public void nextPixel() {
/* 244 */     this.x++;
/* 245 */     this.localX++;
/*     */   }
/*     */   
/*     */   public void jumpPixels(int num) {
/* 249 */     int jumpX = this.x + num;
/* 250 */     if (jumpX < this.bounds.x || jumpX > this.lastX)
/* 252 */       throw new IndexOutOfBoundsException(JaiI18N.getString("RectIterFallback0")); 
/* 255 */     this.x = jumpX;
/* 256 */     this.localX += num;
/* 258 */     if (this.x < this.prevXBoundary || this.x > this.nextXBoundary) {
/* 259 */       this.tileX = PlanarImage.XToTileX(this.x, this.tileGridXOffset, this.tileWidth);
/* 262 */       setTileXBounds();
/* 263 */       setDataBuffer();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean finishedPixels() {
/* 268 */     if (this.x > this.nextXBoundary) {
/* 269 */       if (this.x > this.lastX)
/* 270 */         return true; 
/* 272 */       this.tileX++;
/* 273 */       this.tileXStart += this.tileWidth;
/* 274 */       this.tileXEnd += this.tileWidth;
/* 275 */       this.prevXBoundary = Math.max(this.tileXStart, this.bounds.x);
/* 276 */       this.nextXBoundary = Math.min(this.tileXEnd, this.lastX);
/* 277 */       setDataBuffer();
/* 278 */       return false;
/*     */     } 
/* 281 */     return false;
/*     */   }
/*     */   
/*     */   public boolean nextPixelDone() {
/* 286 */     nextPixel();
/* 287 */     return finishedPixels();
/*     */   }
/*     */   
/*     */   public void startBands() {
/* 291 */     this.b = 0;
/*     */   }
/*     */   
/*     */   public void nextBand() {
/* 295 */     this.b++;
/*     */   }
/*     */   
/*     */   public boolean nextBandDone() {
/* 299 */     nextBand();
/* 300 */     return finishedBands();
/*     */   }
/*     */   
/*     */   public boolean finishedBands() {
/* 304 */     return (this.b >= this.numBands);
/*     */   }
/*     */   
/*     */   public int getSample() {
/* 308 */     return this.sampleModel.getSample(this.localX, this.localY, this.b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public int getSample(int b) {
/* 312 */     return this.sampleModel.getSample(this.localX, this.localY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public float getSampleFloat() {
/* 316 */     return this.sampleModel.getSampleFloat(this.localX, this.localY, this.b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public float getSampleFloat(int b) {
/* 320 */     return this.sampleModel.getSampleFloat(this.localX, this.localY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public double getSampleDouble() {
/* 324 */     return this.sampleModel.getSampleDouble(this.localX, this.localY, this.b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public double getSampleDouble(int b) {
/* 328 */     return this.sampleModel.getSampleDouble(this.localX, this.localY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public int[] getPixel(int[] iArray) {
/* 332 */     return this.sampleModel.getPixel(this.localX, this.localY, iArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public float[] getPixel(float[] fArray) {
/* 336 */     return this.sampleModel.getPixel(this.localX, this.localY, fArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public double[] getPixel(double[] dArray) {
/* 340 */     return this.sampleModel.getPixel(this.localX, this.localY, dArray, this.dataBuffer);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RectIterFallback.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */